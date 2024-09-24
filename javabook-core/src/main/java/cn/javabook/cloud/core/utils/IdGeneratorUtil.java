package cn.javabook.cloud.core.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.javabook.cloud.core.parent.BaseUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Snowflake
 * <p>
 * 分布式系统中，有一些需要使用全局唯一ID的场景，这种时候为了防止ID冲突可以使用36位的UUID，但是UUID有一些缺点，首先他相对比较长，另外UUID一般是无序的
 * 些时候我们希望能使用一种简单一些的ID，并且希望ID能够按照时间有序生成
 * 而twitter的snowflake解决了这种需求，最初Twitter把存储系统从MySQL迁移到Cassandra，因为Cassandra没有顺序ID生成机制，所以开发了这样一套全局唯一ID生成服务
 * <p>
 * snowflake的结构如下(每部分用-分开):
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 第一位为未使用
 * 接下来的41位为毫秒级时间(41位的长度可以使用69年)
 * 然后是5位datacenterId和5位machineId(10位的长度最多支持部署1024个节点）
 * 最后12位是毫秒内的计数（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号）
 * 一共加起来刚好64位，为一个Long型（转换成字符串长度为18）
 * snowflake生成的ID整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenterId和machineId作区分），效率较高
 * <p>
 * 改造前：datacenterId	:	5位，0~31
 * 改造前：machineId		:	5位，0~31
 * 改造前：sequence		:	12位，0~4095
 *
 * 改造后：datacenterId	:	8位，0~255
 * 改造后：machineId		:	4位，0~15
 * 改造前：sequence		:	10位，0~1023
 *
 * @author twitter.com
 */
public class IdGeneratorUtil extends BaseUtil {
	@JsonIgnore
	private static final long serialVersionUID = 3248662879702206153L;

	private final long twepoch = 1272416730000L;
	private final long datacenterIdBits = 8L;
	private final long machineIdBits = 4L;
	private final long sequenceBits = 10L;
	private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private final long maxMachineId = -1L ^ (-1L << machineIdBits);
	private final long machineIdShift = sequenceBits;
	private final long datacenterIdShift = sequenceBits + machineIdBits;
	private final long timestampLeftShift = sequenceBits + machineIdBits + datacenterIdBits;
	private final long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long datacenterId;
	private long machineId;
	private long sequence = 0L;
	private long lastTimestamp = -1L;

	private static IdGeneratorUtil instance = null;
	private static Map<String, IdGeneratorUtil> map = new ConcurrentHashMap<>();

	public static final IdGeneratorUtil getInstance(long datacenterId, long machineId) {
		instance = map.get(datacenterId + "-" + machineId);
		if (null == instance) {
			instance = new IdGeneratorUtil(datacenterId, machineId);
			map.put(datacenterId + "-" + machineId, instance);
		}

		return instance;
	}

	private IdGeneratorUtil(long datacenterId, long machineId) {
		// sanity check for datacenterId
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		// sanity check for machineId
		if (machineId > maxMachineId || machineId < 0) {
			throw new IllegalArgumentException(String.format("machine Id can't be greater than %d or less than 0", maxMachineId));
		}

		this.datacenterId = datacenterId;
		this.machineId = machineId;
	}

	public synchronized long nextId() {
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			logger.error(String.format("clock is moving backwards. Rejecting requests until %d.", lastTimestamp));
			throw new RuntimeException(String.format("clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}

		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tillNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0L;
		}
		lastTimestamp = timestamp;
		return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (machineId << machineIdShift) | sequence;
	}

	protected long tillNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	protected long timeGen() {
		return System.currentTimeMillis();
	}
}

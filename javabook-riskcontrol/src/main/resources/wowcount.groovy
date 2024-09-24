import java.text.SimpleDateFormat
import java.text.ParseException

/**
 * 统计游戏玩家
 *
 */
class PlayerCount {
    // 氪金玩家
    def cost = 8_0000
    // 当天日期
    def today = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
    // 用户基本信息，这些数据可以来自数据库
    def user = ['id'            : '12345678',
                'username'      : '李星云',
                'gender'        : 'M',
                'mobile'        : '13888888888',
                'birth'         : '2001-01-01',
                'address'       : 'XX省XX市XXX区',
                'gold'          : 1000_0000,
                'regtime'       : '2020-01-26 22:08:00',
                'copy'          : [
                        [name: '影牙城堡', 'times': 17],
                        [name: '血色修道院', 'times': 21],
                        [name: '剃刀高地', 'times': 4],
                        [name: '玛拉顿', 'times': 8],
                        [name: '通灵学院', 'times': 7],
                        [name: '斯坦索姆', 'times': 26],
                        [name: '黑石塔', 'times': 14],
                        [name: '祖尔格拉布', 'times': 16],
                        [name: '熔火之心', 'times': 8],
                        [name: '黑翼之巢', 'times': 8],
                        [name: '安琪拉', 'times': 19],
                ],
                'logindate': ['2023-05-01 09:00:02', '2023-06-03 08:50:03',
                              '2023-07-15 09:12:34', '2023-08-29 07:30:11']
    ]

    // 日期解析函数，取得当前年份
    static def date1(_date) {
        try {
            (new SimpleDateFormat("yyyy-MM-dd")).parse(_date).year;
        } catch (ParseException e) {
            e.printStackTrace()
        }
    }

    // 日期解析函数，取得当前时间戳
    static def date2(_date) {
        try {
            // 先尝试一种时间格式
            (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(_date).time;
        } catch (ParseException e) {
            // 再尝试另一种时间格式
            return (new SimpleDateFormat("yyyy-MM-dd")).parse(_date).time;
        }
    }

    // 定义规则
    def rules = [
            // 子规则：基本信息
            'info':
                    [
                            // 判断性别
                            'gender'  : user.gender == 'M' ? '男' : '女',
                            // 计算年龄
                            'age'     : date1(today) - date1(user.birth),
                            // 计算天数
                            'regday'  : ((date2(today) - date2(user.regtime)) / (1000 * 60 * 60 * 24)).intValue()
                    ],

            // 子规则：玩家状态
            'status': [
                    '消费档次': cost >= 5_0000 ? 'A' : (cost >= 4_0000 && cost < 5_0000 ? 'B' : (cost >= 2_0000 && cost < 4_0000) ? 'C' : 'D'),
                    '拥有金币': {
                        if (user.gold >= 500_0000) '至尊王者'
                        else if (user.gold >= 100_0000) '王者'
                        else if (user.gold >= 50_0000) '黄金'
                        else if (user.gold >= 30_0000) '白银'
                        else '青铜'
                    }(),
                    '登录次数':user.logindate.size(),
                    '刷副本数': {
                        int totaltimes = 0;
                        // 遍历计数
                        user.copy.each { totaltimes += it.times }
                        totaltimes
                    }(),
                    '最爱副本': {
                        int number = 0;
                        // 遍历比较大小
                        user.copy.each { number = Math.max(number, it.times) }
                        user.copy.find({ it.times == number })
                    }(),
            ]
    ]
}

def writeline() {
    def player = new PlayerCount()
    def username = player.user.username
    def gender = player.rules.info['gender']
    def age = player.rules.info.age
    def regday = player.rules.info.regday
    def gold = player.rules.status.拥有金币
    def cost = player.rules.status.消费档次
    def times = player.rules.status.登录次数
    def total = player.rules.status.刷副本数
    def max = player.rules.status.最爱副本
    // 在这里还可以进一步进行统计，如某些组合条件满足则打印信息
    println username + " : " + gender
    println "玩家信息.实际年龄 = $age"
    println "玩家信息.游戏天数 = $regday"
    println "玩家状态.拥有金币 = $gold"
    println "玩家状态.消费档次 = $cost"
    println "玩家状态.登录次数 = $times"
    println "玩家状态.刷副本数 = $total"
    println "玩家状态.最爱副本 = $max"
}
writeline()

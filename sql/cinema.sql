-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 47.101.183.63    Database: cinema
-- ------------------------------------------------------
-- Server version	5.7.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `activity`
--

SET @@session.sql_mode =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity`
(
    `id`            int(11)      NOT NULL AUTO_INCREMENT,
    `activity_name` varchar(45)  NOT NULL,
    `a_description` varchar(255) NOT NULL,
    `end_time`      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `coupon_id`     int(11)               DEFAULT NULL,
    `start_time`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity`
    DISABLE KEYS */;
INSERT INTO `activity`
VALUES (2, '春季外卖节', '春季外卖节', '2019-07-23 17:55:59', 5, '2019-06-20 17:55:59'),
       (3, '春季外卖节', '春季外卖节', '2019-07-23 17:55:59', 6, '2019-06-20 17:55:59'),
       (4, '测试活动', '测试活动', '2019-07-26 16:00:00', 8, '2019-06-20 16:00:00');
/*!40000 ALTER TABLE `activity`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_movie`
--

DROP TABLE IF EXISTS `activity_movie`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_movie`
(
    `activity_id` int(11) DEFAULT NULL,
    `movie_id`    int(11) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_movie`
--

LOCK TABLES `activity_movie` WRITE;
/*!40000 ALTER TABLE `activity_movie`
    DISABLE KEYS */;
INSERT INTO `activity_movie`
VALUES (2, 10),
       (2, 11),
       (2, 16),
       (4, 10);
/*!40000 ALTER TABLE `activity_movie`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon`
(
    `id`              int(11)   NOT NULL AUTO_INCREMENT,
    `description`     varchar(255)   DEFAULT NULL,
    `name`            varchar(45)    DEFAULT NULL,
    `target_amount`   float          DEFAULT NULL,
    `discount_amount` float          DEFAULT NULL,
    `start_time`      timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `end_time`        timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon`
    DISABLE KEYS */;
INSERT INTO `coupon`
VALUES (1, '春季电影节', '春季电影节', 20, 5, '2019-05-20 17:47:54', '2019-07-23 17:47:59'),
       (5, '进口电影月', '好莱坞联盟', 30, 4, '2019-05-20 21:14:46', '2019-07-24 21:14:51'),
       (6, '夏恋电影节', '夏恋嘉年华', 50, 10, '2019-05-20 21:15:11', '2019-07-21 21:14:56'),
       (8, '新客钜惠', '开业优惠', 100, 80, '2019-05-20 16:00:00', '2019-07-26 16:00:00');
/*!40000 ALTER TABLE `coupon`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user`
--

DROP TABLE IF EXISTS `coupon_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon_user`
(
    `coupon_id` int(11) NOT NULL,
    `user_id`   int(11) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user`
--

LOCK TABLES `coupon_user` WRITE;
/*!40000 ALTER TABLE `coupon_user`
    DISABLE KEYS */;
INSERT INTO `coupon_user`
VALUES (8, 15),
       (5, 15),
       (8, 15),
       (6, 15),
       (5, 15),
       (8, 15),
       (6, 15);
/*!40000 ALTER TABLE `coupon_user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hall`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall`
    DISABLE KEYS */;
INSERT INTO `hall`
VALUES (1, '1号厅'),
       (2, '2号厅');
/*!40000 ALTER TABLE `hall`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall_seat`
--

DROP TABLE IF EXISTS `hall_seat`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hall_seat`
(
    `hall_id`      int(11) NOT NULL,
    `row_index`    int(4)  NOT NULL,
    `column_index` int(4)  NOT NULL,
    PRIMARY KEY (`hall_id`, `row_index`, `column_index`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_like`
--

LOCK TABLES `hall_seat` WRITE;
/*!40000 ALTER TABLE `hall_seat`
    DISABLE KEYS */;
INSERT INTO `hall_seat`
VALUES (1, 0, 0),
       (1, 0, 1),
       (1, 0, 2),
       (1, 0, 3),
       (1, 0, 4),
       (1, 0, 5),
       (1, 0, 6),
       (1, 0, 7),
       (1, 0, 8),
       (1, 0, 9),
       (1, 1, 0),
       (1, 1, 1),
       (1, 1, 2),
       (1, 1, 3),
       (1, 1, 4),
       (1, 1, 5),
       (1, 1, 6),
       (1, 1, 7),
       (1, 1, 8),
       (1, 1, 9),
       (1, 2, 0),
       (1, 2, 1),
       (1, 2, 2),
       (1, 2, 3),
       (1, 2, 4),
       (1, 2, 5),
       (1, 2, 6),
       (1, 2, 7),
       (1, 2, 8),
       (1, 2, 9),
       (1, 3, 0),
       (1, 3, 1),
       (1, 3, 2),
       (1, 3, 3),
       (1, 3, 4),
       (1, 3, 5),
       (1, 3, 6),
       (1, 3, 7),
       (1, 3, 8),
       (1, 3, 9),
       (1, 4, 0),
       (1, 4, 1),
       (1, 4, 2),
       (1, 4, 3),
       (1, 4, 4),
       (1, 4, 5),
       (1, 4, 6),
       (1, 4, 7),
       (1, 4, 8),
       (1, 4, 9),
       (2, 0, 0),
       (2, 0, 1),
       (2, 0, 2),
       (2, 0, 3),
       (2, 0, 4),
       (2, 0, 5),
       (2, 0, 6),
       (2, 0, 7),
       (2, 0, 8),
       (2, 0, 9),
       (2, 0, 10),
       (2, 0, 11),
       (2, 1, 0),
       (2, 1, 1),
       (2, 1, 2),
       (2, 1, 3),
       (2, 1, 4),
       (2, 1, 5),
       (2, 1, 6),
       (2, 1, 7),
       (2, 1, 8),
       (2, 1, 9),
       (2, 1, 10),
       (2, 1, 11),
       (2, 2, 0),
       (2, 2, 1),
       (2, 2, 2),
       (2, 2, 3),
       (2, 2, 4),
       (2, 2, 5),
       (2, 2, 6),
       (2, 2, 7),
       (2, 2, 8),
       (2, 2, 9),
       (2, 2, 10),
       (2, 2, 11),
       (2, 3, 0),
       (2, 3, 1),
       (2, 3, 2),
       (2, 3, 3),
       (2, 3, 4),
       (2, 3, 5),
       (2, 3, 6),
       (2, 3, 7),
       (2, 3, 8),
       (2, 3, 9),
       (2, 3, 10),
       (2, 3, 11),
       (2, 4, 0),
       (2, 4, 1),
       (2, 4, 2),
       (2, 4, 3),
       (2, 4, 4),
       (2, 4, 5),
       (2, 4, 6),
       (2, 4, 7),
       (2, 4, 8),
       (2, 4, 9),
       (2, 4, 10),
       (2, 4, 11),
       (2, 5, 0),
       (2, 5, 1),
       (2, 5, 2),
       (2, 5, 3),
       (2, 5, 4),
       (2, 5, 5),
       (2, 5, 6),
       (2, 5, 7),
       (2, 5, 8),
       (2, 5, 9),
       (2, 5, 10),
       (2, 5, 11),
       (2, 6, 0),
       (2, 6, 1),
       (2, 6, 2),
       (2, 6, 3),
       (2, 6, 4),
       (2, 6, 5),
       (2, 6, 6),
       (2, 6, 7),
       (2, 6, 8),
       (2, 6, 9),
       (2, 6, 10),
       (2, 6, 11),
       (2, 7, 0),
       (2, 7, 1),
       (2, 7, 2),
       (2, 7, 3),
       (2, 7, 4),
       (2, 7, 5),
       (2, 7, 6),
       (2, 7, 7),
       (2, 7, 8),
       (2, 7, 9),
       (2, 7, 10),
       (2, 7, 11);
/*!40000 ALTER TABLE `hall_seat`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie`
(
    `id`            int(11)      NOT NULL AUTO_INCREMENT,
    `poster_url`    varchar(255)          DEFAULT NULL,
    `director`      varchar(255)          DEFAULT NULL,
    `screen_writer` varchar(255)          DEFAULT NULL,
    `starring`      varchar(255)          DEFAULT NULL,
    `type`          varchar(255)          DEFAULT NULL,
    `country`       varchar(255)          DEFAULT NULL,
    `language`      varchar(255)          DEFAULT NULL,
    `length`        int(11)      NOT NULL,
    `start_date`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `name`          varchar(255) NOT NULL,
    `description`   text,
    `status`        int(11)               DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie`
    DISABLE KEYS */;
INSERT INTO `movie`
VALUES (10, 'http://n.sinaimg.cn/translate/640/w600h840/20190312/ampL-hufnxfm4278816.jpg', '大森贵弘 /伊藤秀樹', '',
        '神谷浩史 /井上和彦 /高良健吾 /小林沙苗 /泽城美雪', '动画', NULL, NULL, 120, '2019-04-14 14:54:31', '夏目友人帐',
        '在人与妖怪之间过着忙碌日子的夏目，偶然与以前的同学结城重逢，由此回忆起了被妖怪缠身的苦涩记忆。此时，夏目认识了在归还名字的妖怪记忆中出现的女性·津村容莉枝。和玲子相识的她，现在和独子椋雄一同过着平稳的生活。夏目通过与他们的交流，心境也变得平和。但这对母子居住的城镇，却似乎潜伏着神秘的妖怪。在调查此事归来后，寄生于猫咪老师身体的“妖之种”，在藤原家的庭院中，一夜之间就长成树结出果实。而吃掉了与自己形状相似果实的猫咪老师，竟然分裂成了3个',
        0),
#        (11, '', '安娜·波顿', NULL, '布利·拉尔森', '动作/冒险/科幻', NULL, NULL, 120, '2019-04-16 14:55:31', '惊奇队长',
#         '漫画中的初代惊奇女士曾经是一名美国空军均情报局探员，暗恋惊奇先生。。。', 0),
#        (12, '', '1', NULL, '1', '1', NULL, NULL, 120, '2019-04-16 14:57:31', '1', '1', 0),
#        (13, '2', '2', NULL, '2', '2', NULL, NULL, 120, '2019-04-16 14:52:31', '2', '2', 0),
#        (14, '', '2', NULL, '2', '2', NULL, NULL, 120, '2019-04-18 13:23:15', '2', '2', 1),
#        (15, '1', '1', '1', '1', '1', '1', '1', 111, '2019-04-16 15:00:24', 'nnmm,,,', '1', 0),
#        (16, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.webp', '林孝谦', 'abcˆ', '陈意涵', '爱情',
#         '大陆', NULL, 123, '2019-04-18 13:23:15', '比悲伤更悲伤的故事',
#         '唱片制作人张哲凯(刘以豪)和王牌作词人宋媛媛(陈意涵)相依为命，两人自幼身世坎坷只有彼此为伴，他们是亲人、是朋友，也彷佛是命中注定的另一半。父亲罹患遗传重症而被母亲抛弃的哲凯，深怕自己随时会发病不久人世，始终没有跨出友谊的界线对媛媛展露爱意。眼见哲凯的病情加重，他暗自决定用剩余的生命完成他们之间的终曲，再为媛媛找个可以托付一生的好男人。这时，事业有 成温柔体贴的医生(张书豪)适时的出现让他成为照顾媛媛的最佳人选，二人按部就班发展着关系。一切看似都在哲凯的计划下进行。然而，故事远比这里所写更要悲伤......',
#         1),
       (19, 'https://m.media-amazon.com/images/M/MV5BMjAwNDgxNTI0M15BMl5BanBnXkFtZTgwNTY4MDI1NzM@._V1_SY1000_CR0,0,631,1000_AL_.jpg', '西蒙·金伯格', '西蒙·金伯格', '詹姆斯·麦卡沃伊，迈克尔·法斯宾德，詹妮弗·劳伦斯，尼古拉斯·霍尔特', '动作,冒险,科幻', '美国', '英语', 114, '2019-06-12 14:55:31', 'X战警 X-Men: Dark Phoenix', '在一次危及生命的太空营救行动中，琴·葛蕾（苏菲·特纳 饰）被神秘的宇宙力量击中，成为最强大的变种人。此后琴不仅要设法掌控日益增长、极不稳定的力量，更要与自己内心的恶魔抗争，她的失控让整个X战警大家庭分崩离析，也让整个星球陷入毁灭的威胁之中……', 0),
       (20, 'https://m.media-amazon.com/images/M/MV5BOGFjYWNkMTMtMTg1ZC00Y2I4LTg0ZTYtN2ZlMzI4MGQwNzg4XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SY1000_CR0,0,674,1000_AL_.jpg', '迈克尔·道赫蒂', '迈克尔·道赫蒂、麦克思·鲍伦斯坦、扎克·希尔兹', '凯尔·钱德勒，维拉·法梅加，米莉·博比·布朗，渡边谦，章子怡', '科幻,灾难,动作', '美国', '英语', 132, '2019-06-12 14:55:31', '哥斯拉2 Godzilla: King of the Monsters', '随着《哥斯拉》和《金刚：骷髅岛》在全球范围内的成功，华纳兄弟影片公司和传奇影业联手开启了怪兽宇宙系列电影的新篇章—一部史诗级动作冒险巨制。在这部电影中，哥斯拉将和众多大家在流行文化中所熟知的怪兽展开较量。全新故事中，研究神秘动物学的机构“帝王组织”成员将勇敢直面巨型怪兽，其中强大的哥斯拉也将和魔斯拉、拉顿和它的死对头——三头王基多拉展开激烈对抗。当这些只存在于传说里的超级生物再度崛起时，它们将展开王者争霸，人类的命运岌岌可危……', 0),
       (21, 'https://m.media-amazon.com/images/M/MV5BMjQ2ODIyMjY4MF5BMl5BanBnXkFtZTgwNzY4ODI2NzM@._V1_SY1000_CR0,0,674,1000_AL_.jpg', '盖·里奇', '约翰·奥古斯特', '威尔·史密斯，梅纳·马苏德，娜奥米·斯科特，马尔万·肯扎里', '爱情,奇幻,冒险', '美国', '英语', 128, '2019-06-12 14:55:31', '阿拉丁 Aladdin', '在充满异域风情的古代阿拉伯王国，善良的穷小子阿拉丁（莫纳·马苏德 饰）和勇敢的茉莉公主（娜奥米·斯科特 饰）浪漫邂逅，在可以满足主人三个愿望的神灯精灵帮助下，两人踏上了一次寻找真爱和自我的魔幻冒险。', 0),
       (22, 'https://p0.meituan.net/movie/b6e77d67efdc6ac89a52b956ead366ae5785152.jpg@464w_644h_1e_1c', '章笛沙', '章笛沙', '陈飞宇，何蓝逗', '爱情,青春', '中国大陆', '中文', 110, '2019-06-12 14:55:31', '最好的我们 My Best Summer', '每个人的心里大概都藏着一个念念不忘的人。一个偶然被提及的名字，让女摄影师耿耿（何蓝逗 饰）内心掀起万千波澜，触动了回忆的开关，那个撩人心动的少年余淮（陈飞宇 饰）再度闯进她的思绪。那是记忆里最好的时光，“学渣”耿耿和“学霸”余淮成了同桌，还结识了简单（王初伊 饰）、贝塔（周楚濋 饰）、徐延亮（陈帅 饰）。校园里充盈着专属少男少女们的懵懂、青涩、怦然心动和勇敢，耿耿余淮也拥有了他们的约定。高考后，当耿耿满怀期待憧憬约定兑现之时，余淮却忽然消失不见了。七年后两人重逢，余淮当年未说出口的那句话、他不辞而别的秘密，耿耿能否得到解答？这段耿耿于怀的过往，让两人再度面临情感的抉择……', 0),
       (23, 'https://p1.meituan.net/movie/b76f37b5e3484b6837f75ef7b5bf46452066459.jpg@464w_644h_1e_1c', '盖尔·曼库索', '盖尔·曼库索', '丹尼斯·奎德，乔什·盖德，贝蒂·吉尔平，玛格·海根柏格，刘宪华', '剧情,喜剧,家庭', '美国', '英语', 108, '2019-06-12 14:55:31', '一条狗的使命2 A Dog\'s Journey', '小狗贝利延续使命，在主人伊森（丹尼斯·奎德 饰）的嘱托下，通过不断的生命轮回， 执着守护伊森的孙女CJ（凯瑟琳·普雷斯科特 饰），将伊森对孙女的爱与陪伴，当做最重要的使命和意义，最终帮助CJ收获幸福，再次回到主人伊森身边。', 0),
       (24, 'https://p0.meituan.net/movie/3b15f9c8f980805b4977cae72a0f47601686013.jpg@464w_644h_1e_1c', '陈奕仁', '五月天', '温尚翊，石锦航，蔡升晏，刘冠佑，陈信宏，梁家辉，黄渤，林志玲', '纪录片,音乐', '中国台湾', '中文', 112, '2019-06-12 14:55:31', '五月天人生无限公司 Mayday Life', '一部电影拥有超过400万位演员，是怎样的体验？ 人生无限公司，每个参与过的人都是主演。 每个“加班夜”都如此难舍难忘， 演唱会内外，你的人生故事可有什么变与不变？ 五月天“人生无限公司”巡演， 横跨四大洲、历时一年半、上百场巡回、超过400万观众，不断刷新纪录，不断创造新的感动。 这部70亿人的自传，此刻翻越到下一章节：人生无限公司将在大银幕继续营业。', 0);
/*!40000 ALTER TABLE `movie`
    ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `movie_like`
--

DROP TABLE IF EXISTS `movie_like`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_like`
(
    `movie_id`  int(11)   NOT NULL,
    `user_id`   int(11)   NOT NULL,
    `like_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`movie_id`, `user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_like`
--

LOCK TABLES `movie_like` WRITE;
/*!40000 ALTER TABLE `movie_like`
    DISABLE KEYS */;
INSERT INTO `movie_like`
VALUES (10, 12, '2019-03-25 02:40:19'),
       (11, 1, '2019-03-22 09:38:12'),
       (11, 2, '2019-03-23 09:38:12'),
       (11, 3, '2019-03-22 08:38:12'),
       (12, 1, '2019-03-23 09:48:46'),
       (12, 3, '2019-03-25 06:36:22'),
       (14, 1, '2019-03-23 09:38:12'),
       (16, 12, '2019-03-23 15:27:48');
/*!40000 ALTER TABLE `movie_like`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule`
(
    `id`         int(11)   NOT NULL AUTO_INCREMENT,
    `hall_id`    int(11)   NOT NULL,
    `movie_id`   int(11)   NOT NULL,
    `start_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `end_time`   timestamp NOT NULL,
    `fare`       double    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 69
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule`
    DISABLE KEYS */;
INSERT into `schedule`
VALUES (69, 1, 10, '2019-06-17 19:30:00', '2019-06-17 21:30:00', 40),
       (70, 1, 10, '2019-06-14 19:30:00', '2019-06-14 21:30:00', 40);
# INSERT INTO `schedule` VALUES (20,1,12,'2019-04-13 17:00:00','2019-04-13 18:00:00',20.5),(21,1,10,'2019-04-11 12:00:00','2019-04-11 13:00:00',90),(27,1,11,'2019-04-17 18:01:00','2019-04-17 20:01:00',20.5),(28,1,11,'2019-04-19 16:00:00','2019-04-19 18:00:00',20.5),(30,1,11,'2019-04-18 18:01:00','2019-04-18 20:01:00',20.5),(31,1,11,'2019-04-12 16:00:00','2019-04-12 18:00:00',20.5),(32,1,11,'2019-04-12 20:00:00','2019-04-12 22:00:00',20.5),(37,1,11,'2019-04-15 00:00:00','2019-04-15 02:00:00',20.5),(38,1,11,'2019-04-14 17:00:00','2019-04-14 19:00:00',20.5),(40,1,10,'2019-04-10 16:00:00','2019-04-10 18:00:00',20.5),(41,1,11,'2019-04-10 19:00:00','2019-04-10 21:00:00',20.5),(42,1,11,'2019-04-10 22:00:00','2019-04-11 00:00:00',20.5),(43,1,10,'2019-04-11 01:00:00','2019-04-11 03:00:00',20.5),(44,2,10,'2019-04-11 01:00:00','2019-04-11 03:00:00',20.5),(45,2,10,'2019-04-10 22:00:00','2019-04-11 00:00:00',20.5),(46,2,11,'2019-04-10 19:00:00','2019-04-10 21:00:00',20.5),(47,2,11,'2019-04-10 16:00:00','2019-04-10 18:00:00',20.5),(48,2,10,'2019-04-11 13:00:00','2019-04-11 15:59:00',20.5),(50,1,10,'2019-04-15 16:00:00','2019-04-15 19:00:00',2),(51,1,10,'2019-04-17 05:00:00','2019-04-17 07:00:00',9),(52,1,10,'2019-04-18 05:00:00','2019-04-18 07:00:00',9),(53,1,16,'2019-04-19 07:00:00','2019-04-19 10:00:00',9),(54,1,16,'2019-04-16 19:00:00','2019-04-16 22:00:00',9),(55,1,15,'2019-04-17 23:00:00','2019-04-18 01:00:00',9),(56,2,10,'2019-04-19 13:00:00','2019-04-19 15:59:00',20.5),(57,2,10,'2019-04-20 13:00:00','2019-04-20 15:59:00',20.5),(58,2,10,'2019-04-21 13:00:00','2019-04-21 15:59:00',20.5),(61,1,13,'2019-04-20 11:00:00','2019-04-20 13:00:00',25),(62,1,11,'2019-04-20 08:00:00','2019-04-20 10:00:00',25),(63,2,15,'2019-04-20 16:01:30','2019-04-21 05:30:00',30),(64,1,16,'2019-04-22 02:00:00','2019-04-22 05:30:00',30),(65,1,10,'2019-04-23 02:00:00','2019-04-23 05:30:00',30),(66,2,13,'2019-04-21 07:31:29','2019-04-16 15:59:00',20.5),(67,2,10,'2019-04-25 13:00:00','2019-04-25 15:59:00',20.5),(68,2,10,'2019-04-26 13:00:00','2019-04-26 15:59:00',20.5);
/*!40000 ALTER TABLE `schedule`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket`
(
    `user_id`      int(11)        DEFAULT NULL,
    `schedule_id`  int(11)        DEFAULT NULL,
    `column_index` int(11)        DEFAULT NULL,
    `row_index`    int(11)        DEFAULT NULL,
    `state`        tinyint(4)     DEFAULT NULL,
    `coupon_id`    int(11)        DEFAULT NULL,
    `id`           int(11)   NOT NULL AUTO_INCREMENT,
    `time`         timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 63
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket`
    DISABLE KEYS */;
# INSERT INTO `ticket` VALUES (12,50,5,3,2,1,'2019-04-23 13:50:52'),(12,50,5,3,2,2,'2019-04-23 13:50:52'),(12,50,5,3,2,3,'2019-04-23 13:50:52'),(12,50,5,3,2,4,'2019-04-23 13:50:52'),(12,50,5,3,0,5,'2019-04-23 13:50:52'),(15,50,4,3,0,6,'2019-04-23 13:50:52'),(15,58,0,0,1,15,'2019-04-23 13:50:52'),(15,58,2,0,1,16,'2019-04-23 13:50:52'),(15,58,1,1,1,17,'2019-04-23 13:50:52'),(15,58,11,7,1,18,'2019-04-23 13:50:52'),(13,50,4,2,1,19,'2019-04-23 13:50:52'),(15,66,3,2,1,20,'2019-04-23 13:50:52'),(12,50,1,1,1,21,'2019-04-23 13:50:52'),(13,50,4,3,1,22,'2019-04-23 13:50:52'),(15,50,2,2,1,23,'2019-04-23 13:50:52'),(15,58,0,7,0,24,'2019-04-23 13:50:52'),(15,58,5,4,0,25,'2019-04-23 13:50:52'),(15,58,6,4,0,26,'2019-04-23 13:50:52'),(15,58,6,2,0,27,'2019-04-23 13:50:52'),(15,58,7,2,0,28,'2019-04-23 13:50:52'),(15,58,0,4,0,29,'2019-04-23 13:50:52'),(15,58,0,3,0,30,'2019-04-23 13:50:52'),(15,58,0,2,0,31,'2019-04-23 13:50:52'),(15,58,10,0,0,32,'2019-04-23 13:50:52'),(15,58,11,0,0,33,'2019-04-23 13:50:52'),(15,58,8,0,0,34,'2019-04-23 13:50:52'),(15,58,9,0,0,35,'2019-04-23 13:50:52'),(15,58,5,0,0,36,'2019-04-23 13:50:52'),(15,58,6,0,0,37,'2019-04-23 13:50:52'),(15,58,6,7,0,38,'2019-04-23 13:50:52'),(15,58,7,7,0,39,'2019-04-23 13:50:52'),(15,58,8,7,0,40,'2019-04-23 13:50:52'),(15,58,11,4,0,41,'2019-04-23 13:50:52'),(15,58,11,5,0,42,'2019-04-23 13:50:52'),(15,58,9,6,0,43,'2019-04-23 13:50:52'),(15,58,10,6,0,44,'2019-04-23 13:50:52'),(15,58,11,6,0,45,'2019-04-23 13:50:52'),(15,58,3,5,1,46,'2019-04-23 13:50:52'),(15,58,4,5,1,47,'2019-04-23 13:50:52'),(15,58,5,5,1,48,'2019-04-23 13:50:52'),(15,58,11,2,0,49,'2019-04-23 13:50:52'),(15,58,11,3,0,50,'2019-04-23 13:50:52'),(15,58,9,4,0,51,'2019-04-23 13:50:52'),(15,58,9,3,1,52,'2019-04-23 13:50:52'),(15,58,10,3,1,53,'2019-04-23 13:50:52'),(15,65,7,4,0,54,'2019-04-23 13:50:52'),(15,65,8,4,0,55,'2019-04-23 13:50:52'),(15,65,9,4,0,56,'2019-04-23 13:50:52'),(15,65,7,3,0,57,'2019-04-23 13:50:52'),(15,65,8,3,0,58,'2019-04-23 13:50:52'),(15,65,9,3,0,59,'2019-04-23 13:50:52'),(15,65,0,0,1,60,'2019-04-23 13:50:52'),(15,65,0,1,1,61,'2019-04-23 13:50:52'),(15,65,0,2,1,62,'2019-04-23 13:50:52');
/*!40000 ALTER TABLE `ticket`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumption`
--

DROP TABLE IF EXISTS `consumption`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consumption`
(
    `user_id`         int(11)        DEFAULT NULL,
    `total`           DOUBLE         DEFAULT NULL,
    `discount_amount` DOUBLE         DEFAULT NULL,
    `pay_method`      int(2)         DEFAULT NULL,
    `coupon_id`       int(11)        DEFAULT NULL,
    `id`              int(11)   NOT NULL AUTO_INCREMENT,
    `time`            timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 40
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumption`
--
#
# LOCK TABLES `consumption` WRITE;
# /*!40000 ALTER TABLE `consumption` DISABLE KEYS */;
#  INSERT INTO `ticket` VALUES (12,50,5,3,2,1,'2019-04-23 13:50:52'),(12,50,5,3,2,2,'2019-04-23 13:50:52'),(12,50,5,3,2,3,'2019-04-23 13:50:52'),(12,50,5,3,2,4,'2019-04-23 13:50:52'),(12,50,5,3,0,5,'2019-04-23 13:50:52'),(15,50,4,3,0,6,'2019-04-23 13:50:52'),(15,58,0,0,1,15,'2019-04-23 13:50:52'),(15,58,2,0,1,16,'2019-04-23 13:50:52'),(15,58,1,1,1,17,'2019-04-23 13:50:52'),(15,58,11,7,1,18,'2019-04-23 13:50:52'),(13,50,4,2,1,19,'2019-04-23 13:50:52'),(15,66,3,2,1,20,'2019-04-23 13:50:52'),(12,50,1,1,1,21,'2019-04-23 13:50:52'),(13,50,4,3,1,22,'2019-04-23 13:50:52'),(15,50,2,2,1,23,'2019-04-23 13:50:52'),(15,58,0,7,0,24,'2019-04-23 13:50:52'),(15,58,5,4,0,25,'2019-04-23 13:50:52'),(15,58,6,4,0,26,'2019-04-23 13:50:52'),(15,58,6,2,0,27,'2019-04-23 13:50:52'),(15,58,7,2,0,28,'2019-04-23 13:50:52'),(15,58,0,4,0,29,'2019-04-23 13:50:52'),(15,58,0,3,0,30,'2019-04-23 13:50:52'),(15,58,0,2,0,31,'2019-04-23 13:50:52'),(15,58,10,0,0,32,'2019-04-23 13:50:52'),(15,58,11,0,0,33,'2019-04-23 13:50:52'),(15,58,8,0,0,34,'2019-04-23 13:50:52'),(15,58,9,0,0,35,'2019-04-23 13:50:52'),(15,58,5,0,0,36,'2019-04-23 13:50:52'),(15,58,6,0,0,37,'2019-04-23 13:50:52'),(15,58,6,7,0,38,'2019-04-23 13:50:52'),(15,58,7,7,0,39,'2019-04-23 13:50:52'),(15,58,8,7,0,40,'2019-04-23 13:50:52'),(15,58,11,4,0,41,'2019-04-23 13:50:52'),(15,58,11,5,0,42,'2019-04-23 13:50:52'),(15,58,9,6,0,43,'2019-04-23 13:50:52'),(15,58,10,6,0,44,'2019-04-23 13:50:52'),(15,58,11,6,0,45,'2019-04-23 13:50:52'),(15,58,3,5,1,46,'2019-04-23 13:50:52'),(15,58,4,5,1,47,'2019-04-23 13:50:52'),(15,58,5,5,1,48,'2019-04-23 13:50:52'),(15,58,11,2,0,49,'2019-04-23 13:50:52'),(15,58,11,3,0,50,'2019-04-23 13:50:52'),(15,58,9,4,0,51,'2019-04-23 13:50:52'),(15,58,9,3,1,52,'2019-04-23 13:50:52'),(15,58,10,3,1,53,'2019-04-23 13:50:52'),(15,65,7,4,0,54,'2019-04-23 13:50:52'),(15,65,8,4,0,55,'2019-04-23 13:50:52'),(15,65,9,4,0,56,'2019-04-23 13:50:52'),(15,65,7,3,0,57,'2019-04-23 13:50:52'),(15,65,8,3,0,58,'2019-04-23 13:50:52'),(15,65,9,3,0,59,'2019-04-23 13:50:52'),(15,65,0,0,1,60,'2019-04-23 13:50:52'),(15,65,0,1,1,61,'2019-04-23 13:50:52'),(15,65,0,2,1,62,'2019-04-23 13:50:52');
# /*!40000 ALTER TABLE `consumption` ENABLE KEYS */;
# UNLOCK TABLES;

--
-- Table structure for table `consumption_ticket`
--

DROP TABLE IF EXISTS `consumption_ticket`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consumption_ticket`
(
    `consumption_id` int(11) DEFAULT NULL,
    `ticket_id`      int(11) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumption_ticket`
--

# LOCK TABLES `consumption_ticket` WRITE;
# /*!40000 ALTER TABLE `consumption_ticket` DISABLE KEYS */;
# INSERT INTO `consumption_ticket` VALUES (2,10),(2,11),(2,16),(4,10);
# /*!40000 ALTER TABLE `consumption_ticket` ENABLE KEYS */;
# UNLOCK TABLES;


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user`
(
    `id`             int(11)     NOT NULL AUTO_INCREMENT,
    `username`       varchar(50) NOT NULL,
    `password`       varchar(50) NOT NULL,
    `privilegeLevel` int(2)      NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id_uindex` (`id`),
    UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user`
VALUES (1, 'testname', '123456', 1),
       (3, 'test', '123456', 1),
       (5, 'test1', '123456', 1),
       (7, 'test121', '123456', 1),
       (8, 'root', '123456', 0),
       (10, 'roottt', '123123', 1),
       (12, 'zhourui', '123456', 1),
       (13, 'abc123', 'abc123', 1),
       (15, 'dd', '123', 1),
       (16, 'a', 'a', 1);
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view`
(
    `id`  int(11) NOT NULL AUTO_INCREMENT,
    `day` int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view`
--

LOCK TABLES `view` WRITE;
/*!40000 ALTER TABLE `view`
    DISABLE KEYS */;
INSERT INTO `view`
VALUES (1, 7);
/*!40000 ALTER TABLE `view`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_card`
--

DROP TABLE IF EXISTS `vip_card`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_card`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT,
    `type`          char(10) DEFAULT NULL,
    `discount`      float    DEFAULT NULL,
    `target_amount` int      DEFAULT NULL,
    `bonus_amount`  int      DEFAULT NULL,
    `price`         float    default null,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`type`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card`
--

LOCK TABLES `vip_card` WRITE;
/*!40000 ALTER TABLE `vip_card`
    DISABLE KEYS */;
INSERT INTO `vip_card`
VALUES (1, '普通卡', 1, 200, 30, 25),
       (2, '铂金卡', 0.8, 200, 50, 40),
       (3, '黑铁卡', 0.95, 300, 50, 28);
/*!40000 ALTER TABLE `vip_card`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_card_user`
--

DROP TABLE IF EXISTS `vip_card_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_card_user`
(
    `id`        int(11)   NOT NULL AUTO_INCREMENT,
    `user_id`   int(11)            DEFAULT NULL,
    `balance`   float              DEFAULT NULL,
    `card_type` char(10)           default null,
    `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `vip_card_user_id_uindex` (`id`),
    UNIQUE KEY `vip_card_user_user_id_uindex` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card_user`
--

LOCK TABLES `vip_card_user` WRITE;
/*!40000 ALTER TABLE `vip_card_user`
    DISABLE KEYS */;
INSERT INTO `vip_card_user`
VALUES (1, 15, 375, '普通卡', '2019-04-21 13:54:38'),
       (2, 12, 660, '铂金卡', '2019-04-17 18:47:42');
/*!40000 ALTER TABLE `vip_card_user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cinema'
--

--
-- Dumping routines for database 'cinema'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2019-04-24 21:20:52

--
-- Table structure for table `refund`
--

DROP TABLE IF EXISTS `refund`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refund`
(
    `type_id` int(11) NOT NULL,
    `hour`    int(11) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund`
--

LOCK TABLES `refund` WRITE;
/*!40000 ALTER TABLE `refund`
    DISABLE KEYS */;
INSERT INTO `refund`
VALUES (1, 72),
       (2, 24);
/*!40000 ALTER TABLE `refund`
    ENABLE KEYS */;
UNLOCK TABLES;
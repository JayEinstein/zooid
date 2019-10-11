#
create table ``(
    `_id` int AUTO_INCREMENT,
    PRIMARY KEY (`_id`)
);

# 字符组成的字符库
# 字符出现的次数放在了字符语境类型 str_scene appear
create table `str_library`(
    `sl_id` int AUTO_INCREMENT,
    `str` varchar(255), # 字符
    `len` int, # 字符长度
    PRIMARY KEY (`sl_id`)
);

# 重复出现的字符串，记录语境重复
# 重复记录的只是一个整个句子，也就是复读机
create table `snt_repet`(
    `sr_id` int AUTO_INCREMENT,
		`ss_id` int, # 语境id进行计数
		`appear` int,
    PRIMARY KEY (`sr_id`)
);

# 重复出现的字符串，记录语境重复
# 重复记录的只是一个整个句子，也就是复读机
create table `char_repet`(
    `cr_id` int AUTO_INCREMENT,
		`slnk_id` int, # 语境id进行计数
		`appear` int,
    PRIMARY KEY (`cr_id`)
);

# 字符对应的语境类型
create table `str_scene`(
    `ss_id` int AUTO_INCREMENT,
		`sl_id` int,
		`scn_id` int,
		`appear` int, # 出现的总次数
    PRIMARY KEY (`ss_id`)
);

# 字符词性, 区别字符作用类型
create table `str_tag`(
    `st_id` int AUTO_INCREMENT,
		`sl_id` int,
		`wt_id` int,
    PRIMARY KEY (`st_id`)
);

# 字符标签
# 动词，名词，介词，状语，定语，某某短语，成语，流行语，主语，谓语，宾语
create table `word_tag`(
    `wt_id` int AUTO_INCREMENT,
		`name` varchar(20),
    PRIMARY KEY (`wt_id`)
);

# 语境类别
# 新闻，娱乐，科学，古诗
create table `scene`(
    `scn_id` int AUTO_INCREMENT,
		`name` varchar(100),
    PRIMARY KEY (`scn_id`)
);

# 单个字符对应的下一个关联
create table `str_next`(
    `sn_id` int AUTO_INCREMENT,
    `item_id` int, # 单个字符 str_scene
    `next_id` int, # 单个字符的下一个 sl_id
    `appear` int, # 出现的次数
		PRIMARY KEY (`sn_id`)
);

# 单个字符对应的上一个关联
create table `str_prev`(
    `sp_id` int AUTO_INCREMENT,
    `item_id` int, # 单个字符 sl_id
    `prev_id` int, # 单个字符的上一个 sl_id
    `appear` int, # 出现的次数
		PRIMARY KEY (`sp_id`)
);

# 字符双链
# 改存场景字符
create table `str_link`(
    `slnk_id` int AUTO_INCREMENT,
		`scn_id` int, # 加上场景
    `item_id` int, # 单个字符 sl_id
    `next_id` int, # 字符的下一个 sl_id
		`prev_id` int, # 字符的上一个 sl_id
    `appear` int, # 出现的次数
		PRIMARY KEY (`slnk_id`)
);

# 字符更新的时间
# 一个字符对应很多时间，会不会爆炸呢，那就不要了
create table `str_update`(
    `sup_id` int AUTO_INCREMENT,
		`sl_id` int,
		`update_time` datetime,
    PRIMARY KEY (`sup_id`)
);

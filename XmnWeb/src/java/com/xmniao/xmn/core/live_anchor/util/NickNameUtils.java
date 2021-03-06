package com.xmniao.xmn.core.live_anchor.util;

import java.util.Random;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：NickNameUtils
 *
 * 类描述：生成随机昵称工具类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-24上午11:17:28
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class NickNameUtils {

	public static String getNickName() {
		String nickName = null;
		Random random = new Random();
		/* 百家姓 */
		String[] surnames = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈",
				"褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕",
				"施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢",
				"邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范",
				"彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任",
				"袁", "柳", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪",
				"汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时",
				"傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平",
				"黄", "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹",
				"狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅",
				"庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝",
				"闵", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜",
				"郭", "梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高", "夏", "蔡",
				"田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢",
				"莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲",
				"邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉", "钮",
				"龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "于",
				"惠", "甄", "曲", "家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜",
				"松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山",
				"谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊",
				"宫", "宁", "祖", "武", "符", "刘", "景", "詹", "束", "龙", "叶", "幸",
				"司", "韶", "郜", "黎", "蓟", "溥", "印", "宿", "白", "怀", "蒲", "邰",
				"从", "鄂", "索", "咸", "籍", "赖", "卓", "蒙", "池", "乔", "郁", "胥",
				"能", "苍", "双", "闻", "党", "翟", "谭", "姬", "申", "扶", "堵", "冉",
				"宰", "郦", "雍", "却", "璩", "桑", "桂", "濮", "牛", "寿", "通", "边",
				"扈", "燕", "冀", "浦", "尚", "农", "温", "别", "庄", "晏", "柴", "瞿",
				"阎", "充", "慕", "连", "茹", "习", "宦", "艾", "鱼", "容", "向", "古",
				"易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿",
				"满", "弘", "匡", "国", "文", "寇", "广", "端", "鲜", "皇", "亓", "老",
				"是", "秘", "畅", "邝", "还", "宾", "闾", "辜", "纵", "侴", "万俟", "司马",
				"上官", "欧阳", "夏侯", "诸葛", "闻人", "东方", "皇甫", "尉迟", "公羊", "澹台",
				"公冶", "宗正", "濮阳", "淳于", "单于", "太叔", "申屠", "公孙", "仲孙", "轩辕",
				"令狐", "钟离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空", "兀官",
				"司寇", "南门", "呼延", "子车", "颛孙", "端木", "巫马", "公西", "车正", "壤驷",
				"公良", "拓跋", "夹谷", "宰父", "谷梁", "段干", "百里", "东郭", "微生", "梁丘",
				"左丘", "东门", "西门", "南宫", "第五", "公仪", "公乘", "太史", "仲长", "叔孙",
				"屈突", "尔朱", "东乡", "相里", "胡母", "司城", "张廖", "雍门", "贺兰", "屋庐",
				"独孤", "南郭", "北宫", "王孙" };

		String[] names = { "乐蕊", "冬菱", "涵蕾", "芳馥", "天欣", "欣嘉", "小雯", "奇逸",
				"嘉泽", "雅致", "雁菱", "端雅", "芳泽", "德运", "天菱", "丹寒", "尔竹", "从凝",
				"政元容", "灵秀", "向松", "正德", "嘉言", "初雪", "安容", "元龙", "元绿", "晨旭",
				"晓灵", "秋巧", "迎南", "空凌波", "琛丽", "安双", "向槐", "迎夏", "霞月", "玉成",
				"恨蕊", "香天", "官代秋", "嘉怡", "轩秀", "敏达", "醉卉", "彭勃", "初翠", "炎彬",
				"南琴", "春竹", "山槐", "访文", "燕舞", "寒梅", "俊雅", "芬馥", "运乾", "自珍",
				"熠彤", "景中", "梦菲", "梁欣合", "丽佳", "云英", "坚秉", "学义", "涵阳", "高原",
				"华奥", "真如", "吉月", "丽文", "流惠", "天媛", "映秋", "雪萍", "清华", "方正奇",
				"海伦", "光誉", "狐凝荷", "思萱", "怜南", "文丽", "雅唱", "和悌", "雁易", "俊能",
				"跋天蓝", "门博耘", "娴淑", "馨欣", "华藏", "莫初蝶", "鸿煊", "晴雪", "怜烟", "芮美",
				"丘书易", "盼香", "庆生", "幻竹", "梓欣", "悠柔", "紫文", "谷又绿", "琇芳", "晓君",
				"凌青", "傲旋", "夏山", "采文", "家美", "新筠", "灵安", "问芙", "勇军", "宏深",
				"勇锐", "美华", "寄蓉", "丰雅", "芬芬", "逸美", "秀梅", "念文", "和平", "夏波",
				"香桃", "佳思", "水冬", "白萱", "诗蕾", "俊贤", "白容", "芳蕙", "诗双", "君之",
				"访儿", "巍昂", "绮云", "芷珍", "夏柳", "雅彤", "立辉", "西华", "沛凝", "弘亮",
				"虹影", "易梦", "振国", "华茂", "巧兰", "忆柏", "浩宕", "天睿", "运杰", "曼文",
				"弘大", "采南", "尔蝶", "圣杰", "高飞", "夜梅", "宇航", "芷珊", "洛灵", "紫萱",
				"春柔", "秀洁", "羊依丝", "静枫", "姜莹莹", "飞兰", "宏富", "丘乐珍", "安萱", "雅艳",
				"今雨", "梦秋", "念桃", "兰泽", "起运", "向雪", "君昊", "从蕾", "鹏海", "燕晨",
				"从菡", "念霜", "友安", "慧美", "志明", "芷波", "英华", "灵安", "敏叡", "佩珍",
				"芳菲", "迎彤", "安春", "保白亦", "舜寒梦", "蒋嘉瑞", "逄安青", "府雨凝", "文思柔",
				"薄音华", "台芸欣", "隐歌阑", "徐孤阳", "阚小瑜", "代亦巧", "汗俊贤", "己寒梦", "单初珍",
				"资傲白", "桑夏兰", "贝奕奕", "宏碧蓉", "缑一禾", "由舒荣", "汪欣艳", "野紫杉", "来彦红",
				"桂安安", "及慕凝", "区琦巧", "兴三诗", "国芷若", "前谷翠", "庚昭懿", "安映冬", "焉霞绮",
				"宫夏兰", "房冰双", "卿天薇", "通兰蕙", "袁梓榆", "考尔风", "戢清韵", "卞晓旋", "英雨伯",
				"邴睿聪", "蓟听芹", "茂初柔", "僧芷雪", "晁盼晴", "淳于德辉", "旗骊艳", "靖伟诚", "广初雪",
				"曲知慧", "浮运虹", "晏清绮", "钞文耀", "伦攸然", "巨幼安", "务盼晴", "巫莺韵", "仁冷雪",
				"完叶丰", "粘如馨", "暴傲云", "忻流丽", "紫骊茹", "张子骞", "毛音韵", "边宏畅", "烟乐心",
				"闪尔容", "柴芷荷", "生韵磬", "廉方方", "荀妍歌", "速之桃", "毋语山", "牟子轩", "旅清佳",
				"丁以蕊", "尉迟静枫", "圣敏丽", "江丹丹", "秦正平", "桓彦君", "令香卉", "督骏俊", "阙山灵",
				"尉靖柔", "窦英才", "员冷松", "熊秀媛", "禾宇达", "字芷荷", "翁琼芳", "藩诗筠", "糜觅翠",
				"肥清秋", "笃微月", "车雅昶", "俎慧婕", "孔骏英", "梁妙松", "越代芹", "斋乐双", "书安晏",
				"栋华灿", "扶融雪", "桐小翠", "连巧春", "凤翠桃", "答沛凝", "莘韦曲", "励鸿远", "虎哲瀚",
				"丹映安", "喻蕙芸", "刁坚秉", "单于觅晴", "咸新立", "胥秀妮", "戎夏兰", "业雅媚", "东如波",
				"乜晨萱", "乙灵珊", "仍高轩", "闾丘谷菱", "贰洛灵", "曹同方", "衣寒云", "不虹颖", "邵松月",
				"长安萱", "羊初南", "左文景", "奕怀寒", "常爰爰", "巴涵蓄", "真书君", "受河灵", "万俟俊杰",
				"孙浩言", "居秀曼", "所高原", "嘉醉芙", "左丘舒方", "星启颜", "隋嘉茂", "碧鲁智敏",
				"绳良吉", "华半凡", "瞿晴丽", "山娟丽", "行阳伯", "将经亘", "赛念之", "干晓曼", "骆巧风",
				"皇溥心", "九香芹", "宰雅容", "乌雅真仪", "夷棠华", "谌友菱", "漆雕智杰", "刚念寒",
				"于和硕", "剑清怡", "郜承安", "镜丹亦", "琦新筠", "滑巧云", "东门芷烟", "张简英睿",
				"愚霞飞", "遇醉冬", "幸文滨", "古小蕊", "蚁碧螺", "双清逸", "范姜灵槐", "牛孤丹", "无高雅",
				"亢安萱", "溥南蕾", "成春柔", "庞英叡", "郝长莹", "介芸芸", "赏春妤", "但诗晗", "斐修筠",
				"澄奇思", "敏驰逸", "宝斯雅", "蒉韵梅", "错思语", "农瀚彭", "井天瑞", "蒯和雅", "多沛凝",
				"庹逸美", "杭柔丽", "第从丹", "诸以丹", "东方依萱", "亓官玉怡", "载庆雪", "侨博丽",
				"丛寄柔", "隽雨筠", "焦诗桃", "鄢晓筠", "池雅旋", "充欢悦", "路伟才", "营浩漫", "之佩珍",
				"庾安双", "香雁山", "程欣荣", "黄隽洁", "翦琳晨", "光馨兰", "范曼凝", "繁晓燕", "蒲易绿",
				"达平婉", "牧驰轩", "石寄春", "那拉元魁", "施言文", "官正初", "虞凡灵", "定婉仪", "掌暄文",
				"甄语彤", "謇迎曼", "京惜儿", "纳惠君", "检语林", "封书凝", "覃从蓉", "蹉芳苓", "贾怡璐",
				"巫马巧兰", "吉嘉怡", "抗君浩", "谏乐悦", "明绮琴", "买天媛", "拜元容", "任秀竹", "练之云",
				"佴翠梅", "仰幼枫", "典恨风", "佘明轩", "羽映颖", "慈秀英", "邢涵梅", "终寻巧", "续娅欣",
				"冠萍雅", "肖菊华", "罕丁辰", "进尔柳", "改秀洁", "勤同化", "蓬访彤", "歧梓玥", "宁忆彤",
				"时德义", "毓春翠", "饶宾鸿", "空朝旭", "寿醉柳", "线文柏", "贲鑫鹏", "弓君博", "拱初蝶",
				"阮小蕾", "富绮梦", "坚鸿哲", "承鸿风", "鲍陶宜", "祁瑞渊", "包闲丽", "东郭馨荣", "邱新曦",
				"却颐真", "敛星雨", "唐灵波", "司马悦欣", "桥邵美", "詹娟秀", "茆卿月", "仪笑卉", "库怀桃",
				"莱雅惠", "飞天恩", "穰宏才", "风琇莹", "纪蕴美", "皮冷萱", "析德业", "哈骞仕", "汤古香",
				"回白筠", "寒星瑶", "针宏逸", "百里翰海", "席莹白", "应珺娅", "费莫雅宁", "项新之",
				"狄秋蝶", "南门雅彤", "潜溶溶", "昌宣朗", "长孙文虹", "湛怡君", "沃正卿", "宋娅楠",
				"郗诗怀", "滕婉娜", "兆元绿", "革惜天", "裔书仪", "笪璇子", "田恬雅", "马灵凡", "释依辰",
				"理俏美", "彭琲瓃", "校昊天", "凭亦竹", "枚访烟", "雷宏放", "公西卓君", "司夜蓉", "蛮筠心",
				"公冶妙婧", "耿凝雁", "罗英华", "世青柏", "韩夏寒", "犹茜茜", "公英逸", "褚自怡", "韦山兰",
				"庆乐游", "逯春梅", "勇光熙", "貊海白", "愈从筠", "米易梦", "智翰藻", "禹嘉音", "环德寿",
				"颜田然", "少白易", "竭平文", "游迎海", "苟孤萍", "吕竹悦", "度幼霜", "淦水蓝", "茅采波",
				"春琼英", "南宫艳娇", "仲孙痴海", "平乐安", "阎欣然", "大烨熠", "沈典雅", "家芳泽",
				"周子平", "杨思凡", "赫连梓菱", "昝芝宇", "希曼语", "泣尔真", "言映冬", "钮南晴", "诗青旋",
				"玄高洁", "悟芊芊", "白乐章", "聊莺莺", "谭易槐", "粟平卉", "杞清懿", "訾觅儿", "公孙晓桐",
				"频清舒", "求文敏", "涂忆枫", "相清涵", "秋诗霜", "枝宾实", "象凌晓", "申春柏", "崇若灵",
				"邝觅山", "奉昆卉", "节香巧", "尔雅达", "局寒烟", "战晨朗", "恽香梅", "源怀蕾", "有乐人",
				"展致欣", "诸葛诗翠", "后建树", "户秋灵", "锺晴霞", "布家骏", "暨慧巧", "塔清晖", "漫寻雪",
				"友浩思", "百宛筠", "孟敏学", "历静晨", "犁欣可", "止雅凡", "鄂古兰", "从雨凝", "隆绮美",
				"臧代芹", "苦湛英", "道傲易", "六逸丽", "卢元正", "解香波", "闳问夏", "澹台成和", "弘易真",
				"盈恨风", "宿芳馨", "银雁蓉", "出绿兰", "巧问萍", "牢祺瑞", "义今瑶", "慕梓颖", "哀力强",
				"钱伟彦", "钊智渊", "北雅琴", "叶思云", "牵曼云", "委浩大", "洋莹玉", "逢夏真", "余寄蕾",
				"接凌文", "缪馨蓉", "盖乐和", "以运恒", "朱秀媛", "似谧辰", "类刚洁", "帖雅秀", "荣谷雪",
				"衅华晖", "靳从云", "莫醉巧", "夫恨之", "端春蕾", "蔚诗霜", "泥灵珊", "系红螺", "郎春桃",
				"森淳雅", "邗思懿", "奚凡白", "豆芮雅", "羿新之", "禚子宁", "汉以柳", "揭友桃", "浦凌香",
				"柳丹萱", "侍琴雪", "谬天心", "姬梦竹", "五泰然", "守又儿", "函若华", "刑乐家", "方笑阳",
				"伏寻梅", "竹慧美", "班清婉", "邹阳阳", "党凡阳", "丑晗日", "德倩秀", "归新梅", "计雨安",
				"易高义", "韶依心", "苍南烟", "纵英光", "公良光明", "朋皓轩", "泉凝绿", "捷逸云",
				"公叔尔安", "陆书艺", "养嘉宝", "闭语蝶", "苏芳菲", "汝雨梅", "堵觅晴", "舒文惠", "都温文",
				"祖燕妮", "谈润丽", "巩天泽", "孝柔惠", "沐顺美", "念曼寒", "卑亦玉", "绍霞姝", "清嘉良",
				"融雪柳", "雀娜娜", "佟佳运珧", "宛温书", "满笑萍", "寇天睿", "商夜南", "实梦凡", "亥乐荷",
				"章佳耘涛", "段干香巧", "殳静芙", "姚乐成", "位欣美", "眭雅逸", "蓝含双", "镇香洁",
				"鲜于迎彤", "西门晓慧", "功珠玉", "王绮波", "闾晴霞", "闻恨蝶", "庄凌春", "雍绿凝",
				"太叔寄文", "厍天亦", "冷蔓蔓", "疏曼容", "丰敏叡", "孛冬亦", "裴梦寒", "衡海菡", "始高达",
				"母悦畅", "芒正清", "竺幻枫", "厚昂然", "青飞驰", "支欣嘉", "斛安容", "冀乐蓉", "芮巧春",
				"锐问筠", "花念露", "妫语梦", "郯雍恬", "脱海雪", "图门天韵", "泰艳丽", "驹元枫", "武昌黎",
				"仇晗日", "召山兰", "普天巧", "撒元芹", "伯采萱", "仙子薇", "史逸致", "次海凡", "鲁清韵",
				"崔雪枫", "夏侯韫玉", "纳喇水凡", "辜幻露", "马佳华荣", "甫雅艳", "雪贞韵", "洛沛凝",
				"乐正怀蕾", "赤嘉福", "寸如风", "赖忆文", "丘希恩", "鱼康裕", "柏兰娜", "那金玉", "盘凝梦",
				"蔺俊捷", "仝星爵", "堂睿诚", "硕夜南", "扈靖儿", "厉思菱", "郁小蕊", "随昕珏", "睦羡丽",
				"虢代桃", "在明煦", "富察涵忍", "怀晴岚", "鲜德容", "乌孙悦欣", "潭念云", "南琴轩",
				"殷俊楠", "闻人骞尧", "霍元灵", "燕冷荷", "黎月悦", "谷梁夏菡", "帛静枫", "折以珊",
				"渠文林", "宗胤文", "门夏蓉", "菅蕴秀", "势寻桃", "严紫雪", "独雅彤", "弭蕴和", "甲暄和",
				"全诗桃", "欧听春", "司寇馨荣", "礼虹英", "宰父晶晶", "戏欣合", "果天禄", "鞠明艳",
				"铁雅丽", "禄希慕", "樊炫明", "市暄莹", "郭昊然", "壬兴学", "盍旭炎", "梁丘亦绿", "强正谊",
				"尚元青", "蔡骞信", "轩辕琇芳", "称清莹", "卜华晖", "用淑兰", "本初彤", "李悠素", "衷从雪",
				"机冰心", "吾觅松", "邓欣然", "乌水丹", "尹雅静", "潘飞龙", "宦巧香", "休可儿", "摩安祯",
				"波乐松", "徭靖易", "柔光济", "腾乐咏", "伊明喆", "候秋春", "宇琼怡", "天代灵", "郸伟懋",
				"红尔芙", "元寄瑶", "恭安志", "简迎丝", "过玉英", "岑力学", "佼嘉美", "张廖云臻", "钦又柔",
				"祢瑾瑜", "况永福", "叔盼丹", "诺烨煜", "吴长运", "树浩渺", "梅霏霏", "卯兴怀", "老赫然",
				"步雅可", "尤友珊", "俟安卉", "籍初蝶", "剧淳静", "司空唱月", "昔夏之", "合燕婉", "段雪容",
				"束聪睿", "御冰香", "仵以旋", "箕修美", "何宏朗", "蒙文敏", "乾寄蓝", "拓跋鸿骞",
				"壤驷念梦", "刀端雅", "魏谷翠", "辉乐容", "宣洁玉", "傅香薇", "麦孤松", "邛玟丽", "利清心",
				"董月灵", "集安怡", "姒英奕", "留鸣晨", "辛彬彬", "邬青文", "苌惜珊", "凌觅翠", "查贞芳",
				"林清婉", "宓代柔", "夔一璇", "金绮艳", "翟语梦", "法小枫", "邸芷文", "须香桃", "谢若云",
				"登听筠", "问博学", "依天空", "声晨星", "俞乐巧", "褒梦雨", "浑飞雪", "麴永逸", "钭尔烟",
				"微生天罡", "逮梓璐", "禽涵韵", "乘修贤", "卓玲琳", "乐半烟", "招其雨", "萨音景", "侯秋寒",
				"慕容芳馨", "嬴绮琴", "瓮翠绿", "海吉星", "操湉湉", "冼彩妍", "毕静枫", "高梦槐", "蒿承教",
				"索天和", "零浩思", "盛宏达", "倪夜梅", "漆海之", "惠彦灵", "郏听然", "么俨雅", "首雁芙",
				"濮昊昊", "甘乐英", "丙欣美", "墨兰泽", "圭问玉", "季秋珊", "夏子珍", "童天骄", "敖运乾",
				"悉慕山", "陶孤菱", "麻娟秀", "僪冰彦", "公羊睿明", "彤绮丽", "楚歆然", "永听南", "奇颖馨" };

		int index = random.nextInt(surnames.length - 1);
		String surname = surnames[index]; // 获得一个随机的姓氏

		int nextInt = random.nextInt(names.length - 1);
		String name = names[nextInt];// 获得一个随机的名称

		nickName = surname + name;
		return nickName;
	}

}

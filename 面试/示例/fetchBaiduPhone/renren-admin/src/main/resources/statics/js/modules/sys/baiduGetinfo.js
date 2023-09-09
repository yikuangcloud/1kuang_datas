var province_city=[
    {"山东":"济宁,济南,青岛,烟台,滨州,莱芜,日照,潍坊,淄博,德州,威海,东营,菏泽,聊城,临沂,泰安,枣庄"},
    {"北京":"北京"},
    {"上海":"上海"},
    {"天津":"天津"},
    {"重庆":"重庆"},
    {"广东":"广州,深圳,珠海,潮州,中山,东莞,佛山,惠州,汕头,汕尾,韶关,湛江,肇庆,河源,江门,揭阳,茂名,梅州,清远,阳江,云浮"},
    {"浙江":"杭州,宁波,温州,绍兴,台州,嘉兴,金华,丽水,湖州,衢州,舟山"},
    {"江苏":"南京,苏州,无锡,常州,淮安,镇江,扬州,徐州,连云港,南通,宿迁,泰州,盐城"},
    {"河北":"石家庄,保定,沧州,秦皇岛,承德,邯郸,唐山,邢台,廊坊,衡水,张家口"},
    {"河南":"郑州,洛阳,开封,焦作,安阳,南阳,周口,商丘,新乡,鹤壁,平顶山,三门峡,信阳,许昌,驻马店,漯河,濮阳"},
    {"福建":"福州,厦门,泉州,漳州,龙岩,南平,宁德,莆田,三明"},
    {"辽宁":"沈阳,大连,鞍山,丹东,抚顺,本溪,朝阳,铁岭,锦州,辽阳,阜新,葫芦岛,盘锦,营口"},
    {"安徽":"合肥,芜湖,马鞍山,淮南,蚌埠,黄山,阜阳,淮北,铜陵,亳州,宣城,安庆,巢湖,池州,六安,滁州,宿州"},
    {"广西":"南宁,桂林,北海,柳州,梧州,玉林,百色,崇左,贵港,河池,贺州,来宾,防城港,钦州"},
    {"山西":"太原,大同,晋城,晋中,临汾,吕梁,朔州,长治,忻州,阳泉,运城"},
    {"海南":"海口,三亚,琼海,东方,儋州,万宁,文昌,定安县,五指山,屯昌县,澄迈县,临高县,白沙,黎族自治县,昌江黎族自治县,乐东黎族自治县,陵水黎族自治县,琼中黎族苗族自治县,保亭黎族苗族自治县"},
    {"内蒙古":"呼和浩特,包头,赤峰,鄂尔多斯,呼伦贝尔,阿拉善盟,通辽,乌海,兴安盟,巴彦淖尔,乌兰察布盟,锡林郭勒盟"},
    {"吉林":"长春,吉林,四平,通化,白城,白山,辽源,松原,延边朝鲜族自治州"},
    {"黑龙江":"哈尔滨,大庆,佳木斯,鹤岗,牡丹江,黑河,鸡西,七台河,齐齐哈尔,双鸭山,绥化,伊春,大兴安岭"},
    {"湖北":"武汉,黄冈,黄石,荆门,荆州,潜江,宜昌,鄂州,十堰,随州,天门,仙桃,咸宁,襄樊,孝感,神农架林区,恩施土家族苗族自治州"},
    {"湖南":"长沙,常德,株洲,岳阳,郴州,怀化,湘潭,张家界,衡阳,娄底,邵阳,益阳,永州,湘西土家族苗族自治州"},
    {"江西":"南昌,上饶,抚州,赣州,九江,鹰潭,吉安,景德镇,萍乡,新余,宜春"},
    {"宁夏":"银川,固原,石嘴山,吴忠,中卫"},
    {"新疆":"乌鲁木齐,哈密,和田,喀什,吐鲁番,阿克苏,阿拉尔,石河子,五家渠,克拉玛依,图木舒克,昌吉回族自治州,伊犁,哈萨克自治州,巴音郭楞蒙古自治州,博尔塔拉蒙古自治州,克孜勒苏柯尔克孜自治州,塔城地区,阿勒泰地区"},
    {"青海":"西宁,海东,果洛藏族自治州,海北藏族自治州,海南藏族自治州,黄南藏族自治州,玉树藏族自治州,海西蒙古族藏族自治州"},
    {"陕西":"西安,咸阳,汉中,安康,宝鸡,商洛,铜川,渭南,延安,榆林"},
    {"甘肃":"兰州,白银,酒泉,定西,嘉峪关,金昌,庆阳,陇南,平凉,天水,武威,张掖,甘南,藏族自治州,临夏回族自治州"},
    {"四川":"成都,宜宾,绵阳,巴中,攀枝花,达州,德阳,遂宁,广安,广元,乐山,泸州,眉山,南充,内江,雅安,资阳,自贡,甘孜藏族自治州,凉山彝族自治州,阿坝藏族羌族自治州"},
    {"云南":"昆明,保山,丽江,玉溪,昭通,临沧,曲靖,普洱,楚雄彝族自治州,大理白族自治州,迪庆藏族自治州,怒江傈傈族自治州,文山壮族苗族自治州,西双版纳傣族自治州,德宏傣族景颇族自治州,红河哈尼族彝族自治州"},
    {"贵州":"贵阳,安顺,毕节,铜仁,遵义,六盘水,黔东南苗族侗族自治州,黔南布依族苗族自治州,黔西南布依族苗族自治州"},
    {"西藏":"拉萨,阿里,昌都,林芝,那曲,日喀则山南"},
    {"香港":"香港岛,九龙,新界"},
    {"澳门":"澳门半岛,澳门离岛"},
    {"台湾":"台北县,宜兰县,桃园县,新竹县,苗栗县,台中县,彰化县,南投县,云林县,嘉义县,台南县,高雄县,屏东县,台东县,花莲县,澎湖县,基隆市,新竹市,台中市,嘉义市,台南市,台北市,高雄市,金门县,连江县"}
];
for(var i=0;i<province_city.length;i++){
    for(var key in province_city[i]){
        $("#province_options").append("<option>"+key+"</option>");
        if(!$("#city_options").text()){
            var city = province_city[i][key].split(",");
            $("#city_options").empty();
            for(var j=0;j<city.length;j++){
                $("#city_options").append("<option>"+city[j]+"</option>");
            }
        }
    }
}
function  changeCity(){
    var province_options = $("#province_options").val();
    for(var i=0;i<province_city.length;i++){
        for(var key in province_city[i]){
            if(key==province_options){
                var city = province_city[i][key].split(",");
                $("#city_options").empty();
                for(var j=0;j<city.length;j++){
                    $("#city_options").append("<option>"+city[j]+"</option>");
                }
            }
        }
    }

}
var province_city2=[
    {"全部":"全部"},
    {"山东":"济宁,济南,青岛,烟台,滨州,莱芜,日照,潍坊,淄博,德州,威海,东营,菏泽,聊城,临沂,泰安,枣庄"},
    {"北京":"北京"},
    {"上海":"上海"},
    {"天津":"天津"},
    {"重庆":"重庆"},
    {"广东":"广州,深圳,珠海,潮州,中山,东莞,佛山,惠州,汕头,汕尾,韶关,湛江,肇庆,河源,江门,揭阳,茂名,梅州,清远,阳江,云浮"},
    {"浙江":"杭州,宁波,温州,绍兴,台州,嘉兴,金华,丽水,湖州,衢州,舟山"},
    {"江苏":"南京,苏州,无锡,常州,淮安,镇江,扬州,徐州,连云港,南通,宿迁,泰州,盐城"},
    {"河北":"石家庄,保定,沧州,秦皇岛,承德,邯郸,唐山,邢台,廊坊,衡水,张家口"},
    {"河南":"郑州,洛阳,开封,焦作,安阳,南阳,周口,商丘,新乡,鹤壁,平顶山,三门峡,信阳,许昌,驻马店,漯河,濮阳"},
    {"福建":"福州,厦门,泉州,漳州,龙岩,南平,宁德,莆田,三明"},
    {"辽宁":"沈阳,大连,鞍山,丹东,抚顺,本溪,朝阳,铁岭,锦州,辽阳,阜新,葫芦岛,盘锦,营口"},
    {"安徽":"合肥,芜湖,马鞍山,淮南,蚌埠,黄山,阜阳,淮北,铜陵,亳州,宣城,安庆,巢湖,池州,六安,滁州,宿州"},
    {"广西":"南宁,桂林,北海,柳州,梧州,玉林,百色,崇左,贵港,河池,贺州,来宾,防城港,钦州"},
    {"山西":"太原,大同,晋城,晋中,临汾,吕梁,朔州,长治,忻州,阳泉,运城"},
    {"海南":"海口,三亚,琼海,东方,儋州,万宁,文昌,定安县,五指山,屯昌县,澄迈县,临高县,白沙,黎族自治县,昌江黎族自治县,乐东黎族自治县,陵水黎族自治县,琼中黎族苗族自治县,保亭黎族苗族自治县"},
    {"内蒙古":"呼和浩特,包头,赤峰,鄂尔多斯,呼伦贝尔,阿拉善盟,通辽,乌海,兴安盟,巴彦淖尔,乌兰察布盟,锡林郭勒盟"},
    {"吉林":"长春,吉林,四平,通化,白城,白山,辽源,松原,延边朝鲜族自治州"},
    {"黑龙江":"哈尔滨,大庆,佳木斯,鹤岗,牡丹江,黑河,鸡西,七台河,齐齐哈尔,双鸭山,绥化,伊春,大兴安岭"},
    {"湖北":"武汉,黄冈,黄石,荆门,荆州,潜江,宜昌,鄂州,十堰,随州,天门,仙桃,咸宁,襄樊,孝感,神农架林区,恩施土家族苗族自治州"},
    {"湖南":"长沙,常德,株洲,岳阳,郴州,怀化,湘潭,张家界,衡阳,娄底,邵阳,益阳,永州,湘西土家族苗族自治州"},
    {"江西":"南昌,上饶,抚州,赣州,九江,鹰潭,吉安,景德镇,萍乡,新余,宜春"},
    {"宁夏":"银川,固原,石嘴山,吴忠,中卫"},
    {"新疆":"乌鲁木齐,哈密,和田,喀什,吐鲁番,阿克苏,阿拉尔,石河子,五家渠,克拉玛依,图木舒克,昌吉回族自治州,伊犁,哈萨克自治州,巴音郭楞蒙古自治州,博尔塔拉蒙古自治州,克孜勒苏柯尔克孜自治州,塔城地区,阿勒泰地区"},
    {"青海":"西宁,海东,果洛藏族自治州,海北藏族自治州,海南藏族自治州,黄南藏族自治州,玉树藏族自治州,海西蒙古族藏族自治州"},
    {"陕西":"西安,咸阳,汉中,安康,宝鸡,商洛,铜川,渭南,延安,榆林"},
    {"甘肃":"兰州,白银,酒泉,定西,嘉峪关,金昌,庆阳,陇南,平凉,天水,武威,张掖,甘南,藏族自治州,临夏回族自治州"},
    {"四川":"成都,宜宾,绵阳,巴中,攀枝花,达州,德阳,遂宁,广安,广元,乐山,泸州,眉山,南充,内江,雅安,资阳,自贡,甘孜藏族自治州,凉山彝族自治州,阿坝藏族羌族自治州"},
    {"云南":"昆明,保山,丽江,玉溪,昭通,临沧,曲靖,普洱,楚雄彝族自治州,大理白族自治州,迪庆藏族自治州,怒江傈傈族自治州,文山壮族苗族自治州,西双版纳傣族自治州,德宏傣族景颇族自治州,红河哈尼族彝族自治州"},
    {"贵州":"贵阳,安顺,毕节,铜仁,遵义,六盘水,黔东南苗族侗族自治州,黔南布依族苗族自治州,黔西南布依族苗族自治州"},
    {"西藏":"拉萨,阿里,昌都,林芝,那曲,日喀则山南"},
    {"香港":"香港岛,九龙,新界"},
    {"澳门":"澳门半岛,澳门离岛"},
    {"台湾":"台北县,宜兰县,桃园县,新竹县,苗栗县,台中县,彰化县,南投县,云林县,嘉义县,台南县,高雄县,屏东县,台东县,花莲县,澎湖县,基隆市,新竹市,台中市,嘉义市,台南市,台北市,高雄市,金门县,连江县"}
];
for(var i=0;i<province_city2.length;i++){
    for(var key in province_city2[i]){
        $("#province_options2").append("<option>"+key+"</option>");
        if(!$("#city_options2").text()){
            var city = province_city2[i][key].split(",");
            $("#city_options2").empty();
            for(var j=0;j<city.length;j++){
                $("#city_options2").append("<option>"+city[j]+"</option>");
            }
        }
    }
}
function  changeCity2(){
    var province_options = $("#province_options2").val();
    for(var i=0;i<province_city2.length;i++){
        for(var key in province_city2[i]){
            if(key==province_options){
                var city = province_city2[i][key].split(",");
                $("#city_options2").empty();
                for(var j=0;j<city.length;j++){
                    $("#city_options2").append("<option>"+city[j]+"</option>");
                }
            }
        }
    }

}
$(function () {





    $("#jqGridLog").jqGrid({
        url: baseURL + 'sys/baiduget/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true},
            {label: '搜索关键词', name: 'searchKey', index: 'search_key'},
            {label: '省', name: 'province', index: 'province'},
            {label: '市', name: 'city', index: 'city'},
            {label: '类型', name: 'type', index: 'type',formatter: function (cellValue, grid, rows, state) {
                    if (cellValue == 0) {
                        return '关键词直接搜索供应商'
                    } else if (cellValue == 1) {
                        return '关键词搜索带广告供应商'
                    }

                }
            },
            {
                label: '爬取状态', name: 'getStatus',

                edittype: "text",
                formatter: function (cellValue, grid, rows, state) {
                    if (cellValue == 0) {
                        return '未爬取'
                    } else if (cellValue == 1) {
                        return '爬取中'
                    } else if (cellValue == 2) {
                        return '爬取成功'
                    } else {
                        return '爬取失败'
                    }

                }
            },
            {
                label: '下载/查看', name: '', index: 'search_time',
                formatter: function (cellvalue, grid, rows) {
                    if (rows.getStatus == 2) {
                        return "<a  href='../../sys/baidugetinfo/getFile?name=" + rows.id + "'>下载</a>" +
                            "   /   <button  onclick='vm.getLog("+rows.id+")'>查看</button>"

                    }else {
                        return ""
                    }

                }
            },
            {label: '搜索时间', name: 'searchTime', index: 'search_time'}
        ],
        viewrecords: true,
        height: "auto",
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPagerLog",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGridLog").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/baidugetinfo/list',
        datatype: "json",
        postData:{getId:0},
        colModel: [
            {label: 'ID', name: 'id', index: "role_id", key: true,width:30,},
            {label: '标题', name: 'title', index: "role_name", },
            {label: '链接', name: 'infoUrl', sortable: false,formatter:function (aa,dd,ee){ return '<a href="'+aa+'" target="_blank">'+ee.title+'</a>'} }
        ],
        viewrecords: true,
        height: "auto",
        rowNum: 1000,
        rowList: [1000, 30, 50],
        rownumbers: true,
        // rownumWidth: 25,
        // width:"10000px",
        // autowidth:true,
        multiselect: true,
        shrinkToFit: true,//宽度自适应
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});


function reloadGetLog(getId) {
    vm.reload(getId);
}

var vm = new Vue({
    el: '#rrapp',
    data: {
        searchKey: null,
        searchKey1: null,
        q: {
            roleName: null
        },
        showList: true,
        title: null,
        role: {
            deptId: null,
            deptName: null
        }
    },
    methods: {
        getLog: function (getId) {

            reloadGetLog(getId);

           var index= layer.open({
                type: 1,
                maxmin: true,
                area: '800px',
                content: $('#getLog') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            });
           layer.full(index)

        },


        query: function () {
            vm.reload();
        },
        query1: function () {
            vm.reload1();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.role = {deptName: null, deptId: null};

        },
        update: function () {
            var roleId = getSelectedRow();
            if (roleId == null) {
                return;
            }

            vm.showList = false;
            vm.title = "修改";

        },
        del: function () {
            var roleIds = getSelectedRows();
            if (roleIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/role/delete",
                    contentType: "application/json",
                    data: JSON.stringify(roleIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function () {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },

        saveOrUpdate: function () {
            //获取选择的菜单
            var nodes = menu_ztree.getCheckedNodes(true);
            var menuIdList = new Array();
            for (var i = 0; i < nodes.length; i++) {
                menuIdList.push(nodes[i].menuId);
            }
            vm.role.menuIdList = menuIdList;

            //获取选择的数据
            var nodes = data_ztree.getCheckedNodes(true);
            var deptIdList = new Array();
            for (var i = 0; i < nodes.length; i++) {
                deptIdList.push(nodes[i].deptId);
            }
            vm.role.deptIdList = deptIdList;

            var url = vm.role.roleId == null ? "sys/role/save" : "sys/role/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.role),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function () {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        getNewSearch: function () {
            layer.open({
                type: 1,
                area: ['800px', '600px'],
                title: '新建信息获取'
                , content: $('#getNewSearch')
            })
        },
        getNewSearchInfo1: function () {
            var searchStr = $("#searchKey").val();
            var startPage = $("#startPage").val();
            var endPage = $("#endPage").val();
            console.log(startPage)
            if (!searchStr) {
                layer.msg("请输入搜索词")
                return;
            }
            if (startPage || endPage) {
                console.log(startPage)

                startPage = 1;
                endPage = 70;
            }
            if (startPage > endPage) {
                layer.msg("起始页应小于结束页")
                return;
            }
            var loading = layer.msg('<span id="confirm">数据查询中,请稍后...</span>', {
                icon: 16,
                shade: 0.3,
                time: 0
            });
            location.href = baseURL + "export1/?str=" + searchStr + "&startPage=" + startPage + "&endPage=" + endPage;

            setTimeout(function () {
                /*定时器判断导出进度是否完成*/
                var timer = setInterval(function () {

                    $.ajax({
                        url: baseURL + "exportStations/",
                        dataType: "json",
                        type: "post",
                        success: function (data) {
                            if (data["exportFlag"] == 0) {
                                layer.close(loading);
                                layer.closeAll();
                                clearInterval(timer);
                            } else if (data["exportFlag"] == 1) {

                            } else {
                                var datasetSize = data["datasetSize"];
                                var exportTime = data["exportTime"];
                                if (exportTime != null && datasetSize != null) {
                                    $("#confirm").text('导出总页数为:' + datasetSize + '预计耗时:' + exportTime + '秒');
                                }
                            }
                        },
                        error: function (e) {
                            console.log(e.responseText);
                        }
                    });

                }, 1000);
            }, 3000);


        },
        getNewSearchInfo2: function () {
            var searchStr = $("#searchKey").val();
            var province_options = $("#province_options").val();
            var city_options = $("#city_options").val();
            var type = $("#type").val();
            if (!searchStr) {
                layer.msg("请输入搜索词")
                return;
            }
            $.ajax({
                url: baseURL + "isHaveHistory/",
                data: {str: searchStr,type:type,province:province_options,city:city_options},
                dataType: "json",
                type: "post",
                success: function (data) {
                    console.log(data)
                    if (data.code == 500) {
                        layer.msg('该关键词对应列已有记录');
                    } else {
                        layer.msg("已经加入到爬取任务")
                        layer.closeAll();
                        vm.reload1();
                    }
                }
            })


        },
        reload: function (getId) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'searchKey': vm.searchKey,
                    getId:getId
                },
                page: page
            }).trigger("reloadGrid");
        },
        reload1: function () {
            vm.showList = true;
            var page = $("#jqGridLog").jqGrid('getGridParam', 'page');
            var province = $("#province_options2").val();
            var city = $("#city_options2").val();
            var type = $("#type2").val();
            if("全部"==province){
                province = null;
            }
            if("全部"==city){
                city = null;
            }
            if("全部"==type){
                type = null;
            }
            $("#jqGridLog").jqGrid('setGridParam', {
                postData: {'searchKey': vm.searchKey1,'province': province,'city': city,'type': type},
                page: page
            }).trigger("reloadGrid");
        }
    }
});

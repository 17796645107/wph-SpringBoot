$(document).ready(function(){
    $("#header").load("../../view/index/index_header.html");
    $("#footer").load("../../view/index/index_footer.html");

    //加载商品二级分类
    $.ajax({
        type:"get",
        url:"/wph/product/findAllSecondaryCategory/7",
        async:true,
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        success:function (result) {
            if (result["code"]==1) {
                new Vue({
                    el: "#secondary_list",
                    data: {
                        secondarys: result["data"]
                    }
                });
            }
            else {
                alert(result["msg"]);
            }
        },
        error:function (error) {
            alert("提交失败"+error);
        }
    });

    //加载商品数据
    $.ajax({
        type:"get",
        url:"/wph/product/findProductByParentID/23",
        async:true,
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        success:function (result) {
            if (result["code"]==1) {
                new Vue({
                    el: "#product_list",
                    data: {
                        products: result["data"]
                    }
                });
            }
            else {
                alert(result["msg"]);
            }
        },
        error:function (error) {
            alert("提交失败"+error);
        }
    });

    //加载商品尺寸
    $.ajax({
        type:"get",
        url:"/wph/product/findAllProductSizeByPrimaryCategoryId/7",
        async:true,
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        success:function (result) {
            if (result["code"]==1) {
                new Vue({
                    el: "#product_size",
                    data: {
                        product_sizes: result["data"]
                    }
                });
            }
            else {
                alert(result["msg"]);
            }
        },
        error:function (error) {
            alert("提交失败"+error);
        }
    });
});


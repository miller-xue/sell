<html>
    <head>
        <meta charset="utf-8">
        <title>卖家商品列表</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>订单id</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址</th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list orderDTOPage.content as orderDTO>
                                <tr>
                                    <td>${orderDTO.orderId}</td>
                                    <td>${orderDTO.buyerName}</td>
                                    <td>${orderDTO.buyerPhone}</td>
                                    <td>${orderDTO.buyerAddress}</td>
                                    <td>${orderDTO.orderAmount}</td>
                                    <td>${orderDTO.getOrderStatusEnum().message}</td>
                                    <td>${orderDTO.getPayStatusEnum().message}</td>
                                    <td>${orderDTO.createTime}</td>
                                    <td>详情</td>
                                    <td>取消</td>
                                </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
                <#-- 分页 -->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <li>
                            <a href="#">上一页</a>
                        </li>
                        <#list 1..orderDTOPage.totalPages as index>
                            <#if currentPage == index>
                                <li class="disabled" ><a href="#">${index}</a></li>
                            <#else>
                                <li class="disabled" ><a href="/sell/seller/list?page=${index}&size=10">${index}</a></li>
                            </#if>
                        </#list>
                        <li>
                            <a href="#">下一页</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
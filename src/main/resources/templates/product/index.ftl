<html>
    <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#-- 边栏 sidevar-->
    <#include "../common/nav.ftl">
    <#-- 主要内容区content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <#-- form -->
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label for="productName">名称</label>
                            <input name="productName" type="text" class="form-control" id="productName" value="${(productInfo.productName)!""}" />
                        </div>
                        <div class="form-group">
                            <label for="productPrice">价格</label>
                            <input name="productPrice" type="number" class="form-control" id="productPrice" value="${(productInfo.productPrice)!""}" />
                        </div>
                        <div class="form-group">
                            <label for="productStock">库存</label>
                            <input name="productStock" type="number" class="form-control" id="productStock" value="${(productInfo.productStock)!""}" />
                        </div>
                        <div class="form-group">
                            <label for="productDescription">描述</label>
                            <input name="productDescription" type="text" class="form-control" id="productDescription"  value="${(productInfo.productDescription)!""}"/>
                        </div>
                        <div class="form-group">
                            <label for="productIcon">图片</label>
                            <img src="${(productInfo.productIcon)!""}" width="100" height="100">
                            <input name="productIcon" type="text" class="form-control" id="productIcon"  value="${(productInfo.productIcon)!""}"/>
                        </div>
                        <div class="form-group">
                            <label for="categoryType">类目</label>
                            <select name="categoryType" id="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                        <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                            selected
                                        </#if>
                                        >${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <input type="text" name="productId" value="${(productInfo.productId)!""}" hidden>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
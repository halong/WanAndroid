package com.example.mywanandroid.http.retrofit

import com.example.mywanandroid.entity.*
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    /**
     * baseurl 请使用：https://www.wanandroid.com/
     **/

    /**
    1.首页相关
    1.1 首页文章列表
    https://www.wanandroid.com/article/list/0/json

    方法：GET
    参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{curPage}/json")
    fun getArticleList(@Path("curPage") curPage: Int): Call<JsonData<ArticleList>>

    /**
    1.2 首页banner
    https://www.wanandroid.com/banner/json

    方法：GET
    参数：无
     */
    @GET("banner/json")
    fun getBanner(): Call<JsonData<List<Banner>>>


    /**
    1.3 常用网站
    https://www.wanandroid.com/friend/json
    方法：GET
     */
    @GET("friend/json")
    fun getFriend(): Call<JsonData<List<Friend>>>

    /**

    1.4 搜索热词
    即目前搜索最多的关键词。

    https://www.wanandroid.com/hotkey/json

    方法：GET
     */
    @GET("hotkey/json")
    fun getHotkey(): Call<JsonData<List<Hotkey>>>

    /**

    1.5 置顶文章
    https://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    fun getTopArticle(): Call<JsonData<List<TopArticle>>>

    /**
    2. 体系
    2.1 体系数据
    https://www.wanandroid.com/tree/json

    方法：GET
    参数：无

    可直接点击查看示例：https://www.wanandroid.com/tree/json

    主要标识的网站内容的体系结构，二级目录。部分数据参考：

    {
    "children": [
    {
    "children": [],
    "courseId": 13,
    "id": 60, // id会在查看该目录下所有文章时有用
    "name": "Android Studio相关", // 子名称
    "order": 1000,
    "parentChapterId": 150,
    "visible": 1
    },...
    ],
    "courseId": 13,
    "id": 150,
    "name": "开发环境", // 一级的名称
    "order": 1,
    "parentChapterId": 0,
    "visible": 1
    }

     */
    @GET("tree/json")
    fun getTree(): Call<JsonData<Tree>>


    /**
    2.2 知识体系下的文章
    https://www.wanandroid.com/article/list/0/json?cid=60

    方法：GET
    参数：
    cid 分类的id，上述二级目录的id
    页码：拼接在链接上，从0开始。
    例如查看类别：Android Studio下所有的文章：https://www.wanandroid.com/article/list/0/json?cid=60

     */
    @GET("article/list/{curPage}/json")
    fun getTreeArticle(
        @Path("curPage") curPage: Int,
        @Query("cid") cid: Int
    ): Call<JsonData<TreeArticleList>>

    /**
    2.3 按照作者昵称搜索文章
    https://wanandroid.com/article/list/0/json?author=鸿洋

    方法:GET
    页码：拼接在链接上，从0开始。
    author：作者昵称，不支持模糊匹配。
     */
    @GET("article/list/{curPage}/json")
    fun getArticlesByAuthor(
        @Path("curPage") curPage: Int,
        @Query("author") author: String
    ): Call<JsonData<AuthorArticleList>>


    /**
    3. 导航
    3.1 导航数据
    https://www.wanandroid.com/navi/json

    方法：GET
    参数：无
     */
    @GET("navi/json")
    fun getNavi(): Call<JsonData<List<NaviList>>>

    /**
    4. 项目
    4.1 项目分类
    https://www.wanandroid.com/project/tree/json

    方法： GET
    参数： 无

    项目为包含一个分类，该接口返回整个分类。

    [
    {
    "children": [],
    "courseId": 13,
    "id": 294, // 该id在获取该分类下项目时需要用到
    "name": "完整项目", // 该分类名称
    "order": 145000,
    "parentChapterId": 293,
    "visible": 0
    }
    ]
     */
    @GET("project/tree/json")
    fun getProjectTree(): Call<JsonData<List<ProjectTree>>>

    /**
    4.2 项目列表数据
    某一个分类下项目列表数据，分页展示

    https://www.wanandroid.com/project/list/1/json?cid=294

    方法：GET
    参数：
    cid 分类的id，上面项目分类接口
    页码：拼接在链接中，从1开始。
     */
    @GET("project/list/{curPage}/json")
    fun getProjectList(
        @Path("curPage") curPage: Int,
        @Query("cid") cid: Int
    ): Call<JsonData<ProjectList>>

    /**
    5. 登录与注册
    5.1 登录
    https://www.wanandroid.com/user/login

    方法：POST
    参数：
    username，password
    登录后会在cookie中返回账号密码，只要在客户端做cookie持久化存储即可自动登录验证。

    {
    "data": {
    "admin": false,
    "chapterTops": [],
    "coinCount": 0,
    "collectIds": [
    15913,
    15958,
    16015,
    16107,
    16130
    ],
    "email": "",
    "icon": "",
    "id": 80675,
    "nickname": "h_chenglong",
    "password": "",
    "publicName": "h_chenglong",
    "token": "",
    "type": 0,
    "username": "h_chenglong"
    },
    "errorCode": 0,
    "errorMsg": ""
    }
     */
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<JsonData<UserInfo>>

    /**
    5.2 注册
    https://www.wanandroid.com/user/register

    方法：POST
    参数
    username,password,repassword

     */


    /**
    5.3 退出
    https://www.wanandroid.com/user/logout/json

    方法：GET
    访问了 logout 后，服务端会让客户端清除 Cookie（即cookie max-Age=0），
    如果客户端 Cookie 实现合理，可以实现自动清理，如果本地做了用户账号密码和保存，及时清理。

    如果需要特殊的errorCode 来支持清除数据，请反馈。

     */


    /**
    6. 收藏
    注意所有收藏相关都需要登录操作，建议登录将返回的cookie（其中包含账号、密码）持久化到本地即可。

    对于需要登录访问的接口，强烈建议阅读下：

    Postman 模拟带 cookie 的请求

     */


    /**
    6.1 收藏文章列表
    https://www.wanandroid.com/lg/collect/list/0/json

    方法：GET
    参数： 页码：拼接在链接中，从0开始。

    在网站上登录后，可以直接访问https://www.wanandroid.com/lg/collect/list/0/json查看自己收藏的文章。

     */


    /**
    6.2 收藏站内文章
    https://www.wanandroid.com/lg/collect/1165/json

    方法：POST
    参数： 文章id，拼接在链接中。
    注意链接中的数字，为需要收藏的id.

     */


    /**
    6.3 收藏站外文章
    https://www.wanandroid.com/lg/collect/add/json

    方法：POST
    参数：
    title，author，link
     */


    /**
    6.4 取消收藏
    取消收藏一共有两个地方可以触发：

     */


    /**
    6.4.1 文章列表
    https://www.wanandroid.com/lg/uncollect_originId/2333/json

    方法：POST
    参数：
    id:拼接在链接上
    id传入的是列表中文章的id。

     */


    /**
    6.4.2 我的收藏页面（该页面包含自己录入的内容）
    https://www.wanandroid.com/lg/uncollect/2805/json

    方法：POST
    参数：
    id:拼接在链接上
    originId:列表页下发，无则为-1
    originId 代表的是你收藏之前的那篇文章本身的id； 但是收藏支持主动添加，这种情况下，没有originId则为-1

     */


    /**
    6.5 收藏网站列表
    https://www.wanandroid.com/lg/collect/usertools/json

    方法：GET
    参数：无
     */


    /**
    6.6 收藏网址
    https://www.wanandroid.com/lg/collect/addtool/json

    方法：POST
    参数：
    name,link

     */


    /**
    6.7 编辑收藏网站
    https://www.wanandroid.com/lg/collect/updatetool/json

    方法：POST
    参数：
    id,name,link
     */


    /**
    6.8 删除收藏网站
    https://www.wanandroid.com/lg/collect/deletetool/json

    方法：POST
    参数：
    id
     */


    /**
    7. 搜索
    7.1 搜索
    https://www.wanandroid.com/article/query/0/json

    方法：POST
    参数：
    页码：拼接在链接上，从0开始。
    k ： 搜索关键词
    注意：支持多个关键词，用空格隔开

     */


    /**
    8. TODO 工具
    最新的 v2版本已经更新，建议使用：玩 Android TODO Open API v2，老接口依然支持，但是已经不再推荐使用。

    注意所有TODO相关都需要登录操作，建议登录将返回的cookie（其中包含账号、密码）持久化到本地即可。

    对于需要登录访问的接口，强烈建议阅读下：

    Postman 模拟带 cookie 的请求*/


    /**
    9.积分 API 2019-08-25
    积分排行榜接口
    https://www.wanandroid.com/coin/rank/1/json

    获取个人积分，需要登录后访问
    https://www.wanandroid.com/lg/coin/userinfo/json

    {
    "data": {
    "coinCount": 451, //总积分
    "rank": 7, //当前排名
    "userId": 2,
    "username": "x**oyang"
    },
    "errorCode": 0,
    "errorMsg": ""
    }
    获取个人积分获取列表，需要登录后访问
    https://www.wanandroid.com//lg/coin/list/1/json

     */


    /**
    10. 广场 2019-10-02
    10.1 广场列表数据
    https://wanandroid.com/user_article/list/页码/json
    GET请求
    页码拼接在url上从0开始
    示例：

    https://wanandroid.com/user_article/list/0/json

    可能出现返回列表数据<每页数据，因为有自见的文章被过滤掉了。
     */


    /**
    10.2 分享人对应列表数据
    这个展示的文章数据都是审核通过的，一般是点击分享人然后展示的列表。

    就像：https://wanandroid.com/user/2/articles/1

    https://www.wanandroid.com/user/2/share_articles/页码/json

    GET请求
    参数：
    用户id: 拼接在url上
    页码拼接在url上从1开始

    返回数据：

    {
    "data": {
    "coinInfo": { // 该用户积分信息
    "coinCount": 20, // 积分总数
    "rank": 1, // 排名
    "userId": 2,
    "username": "x**oyang"
    },
    "shareArticles": { // 该用户分享文章分页信息
    }
    }
    },
    "errorCode": 0,
    "errorMsg": ""
    }
    示例：
    https://www.wanandroid.com/user/2/share_articles/1/json
    可能出现返回列表数据<每页数据，因为有自见的文章被过滤掉了。
     */


    /**
    10.3 自己的分享的文章列表
    https://wanandroid.com/user/lg/private_articles/1/json
    方法：
    GET
    参数：
    页码，从1开始
    如果你登陆了，可以直接点击查看自己分享的列表：
    https://wanandroid.com/user/lg/private_articles/1/json
     */


    /**
    10.4 删除自己分享的文章
    https://wanandroid.com/lg/user_article/delete/9475/json
    请求:POST
    参数：文章id，拼接在链接上
    建议测试方式：登陆网站后，自己分享一篇文章在广场，然后与删除按钮，打开chrome调试模式，查看Network里面有请求。
     */


    /**
    10.5 分享文章
    https://www.wanandroid.com/lg/user_article/add/json
    请求：POST
    参数：
    title:
    link
    注意需要登录后查看，如果为CSDN，简书等链接会直接通过审核，在对外的分享文章列表中展示。
    否则只能在自己的分享文章列表查看，见10.3。
     */


    /**
    11. 问答
    https://wanandroid.com/wenda/list/1/json

    请求:GET

    参数：
    pageId,拼接在链接上，例如上面的1
     */

}
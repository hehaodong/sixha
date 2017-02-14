# sixha
干净的mvp+rxjava+retrofit2
一、项目结构



1、API包，封装Retrofit请求，还有各种拦截器，如日志拦截器、缓存拦截器、异常拦截器等；
2、bean包，存放数据对象，根据阿里巴巴的开发标准，pojo类里面不做任何逻辑和数据初始化；
3、common包，主要有对APP所有activity的管理，存放常量，APP全局配置等；
4、MVP包，存放MVP模式的V层和P层；
5、thirdpart，用来封装第三方包，如图片加载、日志打印等等，这样的好处是，第三方框架一旦更改，仅需更改一个地方；
6、UI包，存放有关view展示的控件，activity、fragment、自定义view等等；
7、util包，各种工具类，图片处理的、数字处理的等。
二、UI标准
1、dimens里面搬来material design里面的尺寸标准


三、第三方
1、下拉刷新：ptr-load-more+recycleview+BaseRecyclerViewAdapterHelper
2、图片加载用picasso
3、tablayout采用一个略屌的第三方，FlycoTabLayout_Lib。



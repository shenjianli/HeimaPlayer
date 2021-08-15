package com.shen.player.constant

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 11:52
 * 描述:该类主要作用
 */
object Constant {

    val girlUrl = "https://gank.io/api/v2/categories/Girl"
    val bannerUrl = "https://gank.io/api/v2/banners"
    val ganHuoUrl = "https://gank.io/api/v2/categories/GanHuo"
    val cateUrl = "https://gank.io/api/v2/categories/Article"
    val girlListUrl = "https://gank.io/api/v2/data/category/Girl/type/Girl/page/1/count/10"

    val cateListUrl =
        "https://gank.io/api/v2/data/category/<category>/type/<type>/page/<page>/count/<count>"

    fun getHomeListUrl(page: Int, num: Int): String {
        return "https://gank.io/api/v2/data/category/Girl/type/Girl/page/$page/count/$num"
    }

    fun getSearchUrl(content: String, page: Int, num: Int): String {
        return "https://gank.io/api/v2/search/$content/category/GanHuo/type/Android/page/$page/count/$num"
    }

    fun getCategoryListUrl(type: String, page: Int, num: Int): String {
        return "https://gank.io/api/v2/data/category/All/type/$type/page/$page/count/$num"
    }

    val videoArray = arrayOf(
        "https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218093206z8V1JuPlpe.mp4",
        "https://stream7.iqilu.com/10339/article/202002/18/2fca1c77730e54c7b500573c2437003f.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218025702PSiVKDB5ap.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/18/202002181038474liyNnnSzz.mp4",
        "https://stream7.iqilu.com/10339/article/202002/18/02319a81c80afed90d9a2b9dc47f85b9.mp4",
        "http://stream4.iqilu.com/ksd/video/2020/02/17/c5e02420426d58521a8783e754e9f4e6.mp4",
        "http://stream4.iqilu.com/ksd/video/2020/02/17/87d03387a05a0e8aa87370fb4c903133.mp4",
        "https://stream7.iqilu.com/10339/article/202002/17/c292033ef110de9f42d7d539fe0423cf.mp4",
        "http://stream4.iqilu.com/ksd/video/2020/02/17/97e3c56e283a10546f22204963086f65.mp4",
        "https://stream7.iqilu.com/10339/article/202002/17/778c5884fa97f460dac8d90493c451de.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/17/20200217021133Eggh6zdlAO.mp4",
        "https://stream7.iqilu.com/10339/article/202002/17/4417a27b1a656f4779eaa005ecd1a1a0.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/17/20200217104524H4D6lmByOe.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/17/20200217101826WjyFCbUXQ2.mp4",
        "http://stream.iqilu.com/vod_bag_2016//2020/02/16/903BE158056C44fcA9524B118A5BF230/903BE158056C44fcA9524B118A5BF230_H264_mp4_500K.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/16/20200216050645YIMfjPq5Nw.mp4",
        "https://stream7.iqilu.com/10339/article/202002/16/3be2e4ef4aa21bfe7493064a7415c34d.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/09/20200209105011F0zPoYzHry.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/09/20200209104902N3v5Vpxuvb.mp4",
        "https://v-cdn.zjol.com.cn/280443.mp4",
        "https://v-cdn.zjol.com.cn/276982.mp4",
        "https://v-cdn.zjol.com.cn/276984.mp4",
        "https://v-cdn.zjol.com.cn/276985.mp4",
        "https://v-cdn.zjol.com.cn/276986.mp4",
        "https://v-cdn.zjol.com.cn/276987.mp4",
        "https://v-cdn.zjol.com.cn/276988.mp4",
        "https://v-cdn.zjol.com.cn/276989.mp4",
        "https://v-cdn.zjol.com.cn/276990.mp4",
        "https://v-cdn.zjol.com.cn/276991.mp4",
        "https://v-cdn.zjol.com.cn/276992.mp4",
        "https://v-cdn.zjol.com.cn/276993.mp4",
        "https://v-cdn.zjol.com.cn/276994.mp4",
        "https://v-cdn.zjol.com.cn/276996.mp4",
        "https://v-cdn.zjol.com.cn/276998.mp4",
        "https://v-cdn.zjol.com.cn/277000.mp4",
        "https://v-cdn.zjol.com.cn/277001.mp4",
        "https://v-cdn.zjol.com.cn/277002.mp4",
        "https://v-cdn.zjol.com.cn/277003.mp4",
        "https://v-cdn.zjol.com.cn/277004.mp4",
        "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
        "http://www.w3school.com.cn/example/html5/mov_bbb.mp4",
        "https://www.w3schools.com/html/movie.mp4",
        "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
        "https://media.w3.org/2010/05/sintel/trailer.mp4",
        "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4",
        "http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4",
        "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4",
        "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4",
        "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4",
        "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4",
        "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4",
        "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4",
        "http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4",
        "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4",
        "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4",
        "http://vfx.mtime.cn/Video/2019/03/13/mp4/190313094901111138.mp4",
        "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4",
        "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312083533415853.mp4",
        "http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4",
        "https://vfx.mtime.cn/Video/2019/01/15/mp4/190115161611510728_480.mp4",
        "https://vfx.mtime.cn/Video/2019/08/24/mp4/190824113155647173.mp4"
    )

}
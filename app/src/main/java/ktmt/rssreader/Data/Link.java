package ktmt.rssreader.Data;

/**
 * Created by Myth on 3/28/2018.
 */

public class Link {
    public static String[] VNEXPRESS= new String[]{
            "https://vnexpress.net/rss/tin-moi-nhat.rss",
            "https://vnexpress.net/rss/thoi-su.rss",
            "https://vnexpress.net/rss/the-gioi.rss",
            "https://vnexpress.net/rss/kinh-doanh.rss",
            "https://vnexpress.net/rss/startup.rss",
            "https://vnexpress.net/rss/giai-tri.rss",
            "https://vnexpress.net/rss/the-thao.rss",
            "https://vnexpress.net/rss/phap-luat.rss",
            "https://vnexpress.net/rss/giao-duc.rss",
            "https://vnexpress.net/rss/suc-khoe.rss",
            "https://vnexpress.net/rss/gia-dinh.rss",
            "https://vnexpress.net/rss/du-lich.rss",
            "https://vnexpress.net/rss/so-hoa.rss",
            "https://vnexpress.net/rss/oto-xe-may.rss",
            "https://vnexpress.net/rss/cong-dong.rss",
            "https://vnexpress.net/rss/tam-su.rss",
            "https://vnexpress.net/rss/cuoi.rss"
    };

    public static String[] VNEXPRESS_TITLE = new String[]{
            "Tin mới nhất",
            "Thời sự",
            "Thế giới",
            "Kinh doanh",
            "Startup",
            "Giải trí",
            "Thể thao",
            "Pháp luật",
            "Giáo dục",
            "Sức khỏe",
            "Gia đình",
            "Du lịch",
            "Số hóa",
            "Ô tô xe máy",
            "Cộng đồng",
            "Tâm sự",
            "Cười"
    };

    public static String[] _24H = new String[]{
            "http://24h.com.vn/upload/rss/trangchu24h.rss",
            "http://24h.com.vn/upload/rss/tintuctrongngay.rss",
            "http://24h.com.vn/upload/rss/bongda.rss",
            "http://24h.com.vn/upload/rss/anninhhinhsu.rss",
            "http://24h.com.vn/upload/rss/thoitrang.rss",
            "http://24h.com.vn/upload/rss/thoitranghitech.rss",
            "http://24h.com.vn/upload/rss/taichinhbatdongsan.rss",
            "http://24h.com.vn/upload/rss/amthuc.rss",
            "http://24h.com.vn/upload/rss/lamdep.rss",
            "http://24h.com.vn/upload/rss/phim.rss",
            "http://24h.com.vn/upload/rss/giaoducduhoc.rss",
            "http://24h.com.vn/upload/rss/bantrecuocsong.rss",
            "http://24h.com.vn/upload/rss/canhacmtv.rss",
            "http://24h.com.vn/upload/rss/thethao.rss",
            "http://24h.com.vn/upload/rss/congnghethongtin.rss",
            "http://24h.com.vn/upload/rss/otoxemay.rss",
            "http://24h.com.vn/upload/rss/thitruongtieudung.rss",
            "http://24h.com.vn/upload/rss/dulich.rss",
            "http://24h.com.vn/upload/rss/suckhoedoisong.rss",
            "http://24h.com.vn/upload/rss/cuoi24h.rss",
            "http://24h.com.vn/upload/rss/tintucquocte.rss",
            "http://24h.com.vn/upload/rss/giaitri.rss"
    };

    public static String[] _24H_TITLE = new String[]{
            "Trang chủ",
            "Tin tức trong ngày",
            "Bóng đá",
            "An ninh",
            "Thời trang",
            "High tech",
            "Tài chính bất động sản",
            "Ẩm thực",
            "Làm đẹp",
            "Phim",
            "Giáo dục du học",
            "Bạn trẻ và cuộc sống",
            "Ca nhạc MTV",
            "Thể thao",
            "Công nghệ thông tin",
            "Ô tô xe máy",
            "Thị trường tiêu dùng",
            "Du lịch",
            "Sức khỏe đời sống",
            "Cười",
            "Thế giới",
            "Giải trí"
    };

    public String getLink(int webId, int channelId)
    {
        if(webId == 1)
        {
            if(channelId >= _24H.length)
            {
                return _24H[0];
            }
            return _24H[channelId];
        }
        else
        {
            if(channelId >= VNEXPRESS.length)
            {
                return VNEXPRESS[0];
            }
            return VNEXPRESS[channelId];
        }
    }

    public String getTitle(int webId, int channelId)
    {
        if(webId == 1)
        {
            if(channelId >= _24H_TITLE.length)
            {
                return _24H_TITLE[0];
            }
            return _24H_TITLE[channelId];
        }
        else
        {
            if(channelId >= VNEXPRESS_TITLE.length)
            {
                return VNEXPRESS_TITLE[0];
            }
            return VNEXPRESS_TITLE[channelId];
        }
    }
}

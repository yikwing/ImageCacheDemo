package com.pinger.imagecachedemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pinger on 2016/9/17.
 */
public class ImageBean{

    /**
     * status : true
     * total : 928
     * tngou : [{"count":1880,"fcount":0,"galleryclass":3,"id":942,"img":"/ext/160907/2882f973abbf200a253c8885b79890da.jpg","rcount":0,"size":8,"time":1473249814000,"title":"极品美女李妍静大胆开叉长裙性感"},{"count":1627,"fcount":0,"galleryclass":4,"id":941,"img":"/ext/160907/9540edbcc44e5776a5fbe336fc479586.jpg","rcount":0,"size":9,"time":1473249371000,"title":"都市白领美女优雅性感"},{"count":1511,"fcount":0,"galleryclass":5,"id":940,"img":"/ext/160907/3ace069126a05af633c9d09fd06792a0.jpg","rcount":0,"size":9,"time":1473249256000,"title":"长腿美女李妍静蕾丝长裙私拍"},{"count":3130,"fcount":0,"galleryclass":4,"id":939,"img":"/ext/160902/da1f2356a348c4d56015d452ef69d0b2.jpg","rcount":0,"size":8,"time":1472815961000,"title":"白嫩长腿美女优雅气质妩媚"},{"count":3112,"fcount":0,"galleryclass":7,"id":938,"img":"/ext/160902/2bed9bfbc5817b72562cbf1e5b5ccc7a.jpg","rcount":0,"size":24,"time":1472815922000,"title":"极品车模性感车展图片合集"},{"count":3569,"fcount":0,"galleryclass":1,"id":937,"img":"/ext/160902/4710af037ca4f1e00c42e2b65a1fbaee.jpg","rcount":0,"size":7,"time":1472815816000,"title":"性感嫩模吊带裙酥胸抢眼撩人"},{"count":3340,"fcount":0,"galleryclass":5,"id":936,"img":"/ext/160902/244b64b03a811f1318b268526a8286a3.jpg","rcount":0,"size":9,"time":1472815780000,"title":"美女秘书性感礼服酥胸美腿极品"},{"count":3788,"fcount":0,"galleryclass":2,"id":935,"img":"/ext/160831/6310cec0fdd32070039f621a939377a4.jpg","rcount":0,"size":11,"time":1472646124000,"title":"韩国美女李妍静最新绝美性感私房美腿写真"},{"count":3631,"fcount":0,"galleryclass":6,"id":934,"img":"/ext/160831/f8ea3a2faed494a49f02b963614949e7.jpg","rcount":0,"size":9,"time":1472646008000,"title":"极品气质美女粉色包裙优雅迷人白嫩美腿性感"},{"count":2477,"fcount":0,"galleryclass":7,"id":933,"img":"/ext/160830/5e6d971c82f5f8085add31e3984fb6ca.jpg","rcount":0,"size":7,"time":1472559690000,"title":"性感美女车模美胸爆乳喷血诱人妩媚迷人"},{"count":2634,"fcount":0,"galleryclass":1,"id":932,"img":"/ext/160830/4caa1b28d02bd8984619efa227258a64.jpg","rcount":0,"size":9,"time":1472559595000,"title":"开叉长裙美女性感沙发撩人艺术"},{"count":2206,"fcount":0,"galleryclass":5,"id":931,"img":"/ext/160830/c200bc17f9fe60fefaa6eb6dda6f02e5.jpg","rcount":0,"size":11,"time":1472559522000,"title":"OL白领美女性感美腿高凉私拍"},{"count":1686,"fcount":0,"galleryclass":1,"id":930,"img":"/ext/160829/945c1333a6675039480d0f137e8ebad9.jpg","rcount":0,"size":9,"time":1472472569000,"title":"气质美女丝绸吊带连衣裙性感写真"},{"count":1495,"fcount":0,"galleryclass":6,"id":929,"img":"/ext/160829/b8c83a88549b1b878ab11e5743757f2f.jpg","rcount":0,"size":4,"time":1472472489000,"title":"大波浪美女性感镂空连衣裙"},{"count":1249,"fcount":0,"galleryclass":5,"id":928,"img":"/ext/160829/eaca856874a6bfad6ae3840be3492930.jpg","rcount":0,"size":9,"time":1472472424000,"title":"大胸美女抹胸短裙魅惑性感写真"},{"count":1250,"fcount":0,"galleryclass":4,"id":927,"img":"/ext/160829/222f9b0de0bf52396432d30b99383fb0.jpg","rcount":0,"size":9,"time":1472472376000,"title":"气质女神薄纱透视裙极品美腿性感"},{"count":1258,"fcount":0,"galleryclass":4,"id":926,"img":"/ext/160829/f675e54aa5116e5e2d2e7fe41604ce80.jpg","rcount":0,"size":9,"time":1472472295000,"title":"大胸极品女神前凸后翘性感写真图片"},{"count":1978,"fcount":0,"galleryclass":1,"id":925,"img":"/ext/160825/92a547eb62f72a3bf01f24a958f23d58.jpg","rcount":0,"size":6,"time":1472136035000,"title":"性感大胸美女车模酥胸喷血诱人背心裙迷人"},{"count":526,"fcount":0,"galleryclass":6,"id":924,"img":"/ext/160825/eef78e0050d91a38a6a863f621da5ba0.jpg","rcount":0,"size":9,"time":1472135993000,"title":"清纯美女模特低胸短裙美胸诱人性感"},{"count":370,"fcount":0,"galleryclass":7,"id":923,"img":"/ext/160825/332008afa216de7e1af46229571d5ea5.jpg","rcount":0,"size":9,"time":1472135947000,"title":"性感车模林奈子美胸傲人与野马销魂"}]
     */

    private boolean status;
    private int total;
    /**
     * count : 1880
     * fcount : 0
     * galleryclass : 3
     * id : 942
     * img : /ext/160907/2882f973abbf200a253c8885b79890da.jpg
     * rcount : 0
     * size : 8
     * time : 1473249814000
     * title : 极品美女李妍静大胆开叉长裙性感
     */

    private List<TngouBean> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean implements Serializable{
        private int count;
        private int fcount;
        private int galleryclass;
        private int id;
        private String img;
        private int rcount;
        private int size;
        private long time;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFcount() {
            return fcount;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public int getGalleryclass() {
            return galleryclass;
        }

        public void setGalleryclass(int galleryclass) {
            this.galleryclass = galleryclass;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getRcount() {
            return rcount;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

package com.pettrip.apiPayload;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "response")
@Getter
@Setter
public class ExternalApiResponse {

    @XmlElement(name = "header")
    private Header header;

    @XmlElement(name = "body")
    private Body body;

    @Getter
    @Setter
    public static class Header {
        @XmlElement(name = "resultCode")
        private String resultCode;

        @XmlElement(name = "resultMsg")
        private String resultMsg;
    }

    @Getter
    @Setter
    public static class Body {
        @XmlElement(name = "item")
        private Item item; // 단수형으로 수정

        @Getter
        @Setter
        public static class Item {
            @XmlElement(name = "dogRegNo")
            private String dogRegNo;

            @XmlElement(name = "rfidCd")
            private String rfidCd;

            @XmlElement(name = "dogNm")
            private String dogNm;

            @XmlElement(name = "sexNm")
            private String sexNm;

            @XmlElement(name = "kindNm")
            private String kindNm;

            @XmlElement(name = "neuterYn")
            private String neuterYn;

            @XmlElement(name = "orgNm")
            private String orgNm;

            @XmlElement(name = "officeTel")
            private String officeTel;

            @XmlElement(name = "aprGbNm")
            private String aprGbNm;
        }
    }
}
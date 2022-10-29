package com.example.nursinghomeapplication.SEnum;

import java.text.MessageFormat;

public class ShortMessage {
    public static String buildSmsTemplateContent(String content, Object[] args) {
        return MessageFormat.format(content, args);
    }

    public static String buildSmsContent(ShortMessageEnum shortMessageEnum, Object[] args) {
        return MessageFormat.format(shortMessageEnum.getBody(), args);
    }

    public static String buildSmsContentmod(String context, Object[] args) {
        return MessageFormat.format(context, args);
    }

    /**
     * 实际业务中的短信模板
     */
    public enum ShortMessageEnum {
        /***
         * 模板
         */
        SM01("养老院管理系统", "您的验证码是:[", "${code},", "]若遗忘密码或非本人操作要打主页客服电话哦，切记啊宝打死不要给别人说啊");

        String title;
        String h2title;
        String code;
        String body;

        ShortMessageEnum(String title, String h2title, String code, String body) {
            this.title = title;
            this.h2title=h2title;
            this.code = code;
            this.body = body;
        }

        public String getH2title() {
            return h2title;
        }

        public void setH2title(String h2title) {
            this.h2title = h2title;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public String toString() {
            return "ShortMessageEnum{" +
                    "title='" + title + '\'' +
                    ", h2title='" + h2title + '\'' +
                    ", code='" + code + '\'' +
                    ", body='" + body + '\'' +
                    '}';
        }
    }

}

package com.mask.mask.Bean;

public class UpdateItemBean {

    private String title;//更新日期
    private String content;//更新的内容
    private String btnText;//按钮的文字
    private int viewColor;//按钮的背景颜色

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    public int getViewColor() {
        return viewColor;
    }

    public void setViewColor(int viewColor) {
        this.viewColor = viewColor;
    }

    public UpdateItemBean(String title, String content, String btnText, int viewColor) {
        this.title = title;
        this.content = content;
        this.btnText = btnText;
        this.viewColor = viewColor;
    }
}

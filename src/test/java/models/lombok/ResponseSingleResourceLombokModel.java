package models.lombok;

import lombok.*;

@Data
public class ResponseSingleResourceLombokModel {
    private String page;
    private String per_page;
    private String total;
    private String total_pages;
    private DatumSingleResourceModel[] data;
    private Support support;

    @Getter
    @Setter
    static class Support {
        private String url;
        private String text;
    }
}

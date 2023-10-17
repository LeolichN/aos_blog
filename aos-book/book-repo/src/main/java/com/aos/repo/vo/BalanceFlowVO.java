package com.aos.repo.vo;


import com.aos.repo.enums.FlowType;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BalanceFlowVO {

    private Integer id;
    private BookVO book;
    private FlowType type;
    private String typeName;
    private String title;
    private String notes;
    private Long createTime;
    private BigDecimal amount;
    private BigDecimal convertedAmount;
    private AccountVO account;
    private Boolean confirm;
    private Boolean include;
//    private List<CategoryRelationDetails> categories;
//    private List<TagRe> tags;
    private String accountName;
    private String categoryName;
    private AccountVO to;
//    private IdAndNameDetails payee;
//
//    public String getListTitle() {
//        StringBuilder result = new StringBuilder();
//        if (StringUtils.hasText(title)) {
//            result.append(title);
//        } else {
//            if (type == FlowType.EXPENSE || type == FlowType.INCOME) {
//                result.append(getCategories().stream().map(CategoryRelationDetails::getCategoryName).collect(Collectors.joining(", ")));
//            } else if (type == FlowType.TRANSFER) {
//                result.append(accountName);
//            } else {
//                result.append("调整余额");
//            }
//        }
//        if (payee != null) {
//            result.append(" - ").append(payee.getName());
//        }
//        return result.toString();
//    }
//
//    public String getTagsName() {
//        return tags.stream().map(TagRelationDetails::getTagName).collect(Collectors.joining(", "));
//    }
//
//    public boolean getNeedConvert() {
//        if (type == FlowType.EXPENSE || type == FlowType.INCOME) {
//            return !Objects.equals(book.getDefaultCurrencyCode(), account != null ? account.getCurrencyCode() : null);
//        } else if (type == FlowType.TRANSFER) {
//            return !Objects.equals(account.getCurrencyCode(), to.getCurrencyCode());
//        }
//        return false;
//    }
//
//    public String getConvertCode() {
//        if (type == FlowType.EXPENSE || type == FlowType.INCOME) {
//            return book.getDefaultCurrencyCode();
//        } else if (type == FlowType.TRANSFER) {
//            return to.getCurrencyCode();
//        }
//        return null;
//    }
//
//    public int getTypeIndex() {
//        return type.ordinal();
//    }

}

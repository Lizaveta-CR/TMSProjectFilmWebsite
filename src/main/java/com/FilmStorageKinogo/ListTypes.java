package com.FilmStorageKinogo;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class ListTypes<T> {
    private String selectField;
    private String endField;
    private Class<T> typeClass;

    public ListTypes(String selectField, String endField, Class<T> typeClass) {
        this.selectField = selectField;
        this.endField = endField;
        this.typeClass = typeClass;
    }

    public String getSelectField() {
        return selectField;
    }

    public void setSelectField(String selectField) {
        this.selectField = selectField;
    }

    public String getEndField() {
        return endField;
    }

    public void setEndField(String endField) {
        this.endField = endField;
    }

    public Class<T> getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    public List<String> getTypes(Element type) {
        List<String> typeList = new ArrayList<>();
        Element types = type.select(selectField).first();
        if (types != null && !types.text().isEmpty()) {
            boolean isElement = typeClass.equals(Element.class);
            Node currentElement = isElement ? (Element) types.nextElementSibling() : types.nextSibling();
            String currentElementValue = isElement ? ((Element) currentElement).text() : currentElement.toString();
            do {
                if (!currentElementValue.isEmpty()) {
                    typeList.add(currentElementValue.trim());
                }
                currentElement = isElement ? ((Element) currentElement).nextElementSibling() : currentElement.nextSibling();
                currentElementValue = isElement ? ((Element) currentElement).text() : currentElement.toString();
            }
            while (!currentElementValue.equals(endField));
        } else {
            typeList.add("No information");
        }
        return typeList;
    }
}

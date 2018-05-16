package com.example.stagiaire040.testdeuxgooglemap.RecyclerView;

public class TypeB implements ListItem {

    String mTextEtape;
    String mTextMetre;


    public TypeB(String textEtape, String textMetre) {
        mTextEtape = textEtape;
        mTextMetre = textMetre;
    }


    public String getTextEtape() {
        return mTextEtape;
    }

    public void setTextEtape(String textEtape) {
        mTextEtape = textEtape;
    }

    public String getTextMetre() {
        return mTextMetre;
    }

    public void setTextMetre(String textMetre) {
        mTextMetre = textMetre;
    }

    @Override
    public int getListItemType() {
        return ListItem.TYPE_B;
    }




}

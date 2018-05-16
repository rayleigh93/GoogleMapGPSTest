package com.example.stagiaire040.testdeuxgooglemap.RecyclerView;

public class TypeA implements ListItem {


    String mTextAdresseDepArr;


    public TypeA(String textAdresseDepArr) {
        mTextAdresseDepArr = textAdresseDepArr;
    }


    public String getTextAdresseDepArr() {
        return mTextAdresseDepArr;
    }

    public void setTextAdresseDepArr(String textAdresseDepArr) {
        mTextAdresseDepArr = textAdresseDepArr;
    }

    @Override
    public int getListItemType() {
        return ListItem.TYPE_A;
    }


}

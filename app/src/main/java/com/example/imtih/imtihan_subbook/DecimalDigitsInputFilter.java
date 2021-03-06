package com.example.imtih.imtihan_subbook;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by imtih on 2018-02-04.
 * Adapted from: https://stackoverflow.com/questions/5357455/limit-decimal-places-in-android-edittext
 * Viewed: 2018-02-02
 * This class is used to constrain the input in the costs section to 2 points after the decimal
 * it also prevents unnaturally large values to be input
 */

public class DecimalDigitsInputFilter implements InputFilter {

    Pattern mPattern;

    public DecimalDigitsInputFilter(int digitsBeforeZero,int digitsAfterZero) {
        mPattern=Pattern.compile("[0-9]{0," + (digitsBeforeZero-1)
                    + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Matcher matcher=mPattern.matcher(dest);
        if(!matcher.matches())
            return "";
        return null;
    }

}
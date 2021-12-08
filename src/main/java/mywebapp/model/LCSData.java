package mywebapp.model;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class LCSData {
    List<StringData> setOfStrings;

    public String findLCS()
    {
        int n = setOfStrings.size();

        String s = setOfStrings.get(0).value;
        int len = s.length();

        String res = "";

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String stem = s.substring(i, j);
                int k = 1;
                for (k = 1; k < n; k++) {
                    if (!setOfStrings.get(k).value.contains(stem))
                        break;
                }
                if (k == n && res.length() < stem.length())
                    res = stem;
            }
        }

        return res;
    }
}

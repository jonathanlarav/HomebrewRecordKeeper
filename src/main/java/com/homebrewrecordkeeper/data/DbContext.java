package com.homebrewrecordkeeper.data;

import java.util.List;

public interface DbContext {
    List<MaltRecord> getMaltRecords();
}

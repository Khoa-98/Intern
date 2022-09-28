package com.vuhien.application.googlesheet;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.vuhien.application.entity.Category;


@Service
public class SheetDataService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void renderProductFromGoogleSheetToDatabase() throws IOException, GeneralSecurityException {

        List<List<Object>> valueRanges = getDataGoogleSheet("category");
        List<Category> categories = convertDataFromGoogleSheetToDatabase(valueRanges);
        batchInsertProduct(categories);

    }

    public List<List<Object>> getDataGoogleSheet(String sheetName) throws IOException, GeneralSecurityException {
        String SPREADSHEET_ID = "1kXjpqzPEglq3BSihQknP6mIzjQoIlWWVZmakpW_HLyk";
        Sheets sheetService = SheetsServiceUtil.getSheetService();

        List<String> ranges = Arrays.asList(sheetName);
        // The default render option is ValueRenderOption.FORMATTED_VALUE.
        String valueRenderOption = "FORMATTED_VALUE"; // TODO: Update placeholder value.

        // The default dateTime render option is [DateTimeRenderOption.SERIAL_NUMBER].
        String dateTimeRenderOption = "FORMATTED_STRING"; // TODO: Update placeholder value.

        BatchGetValuesResponse readResult = sheetService.spreadsheets().values()
                .batchGet(SPREADSHEET_ID)
                .setRanges(ranges)
                .setValueRenderOption(valueRenderOption)
                .setDateTimeRenderOption(dateTimeRenderOption)
                .execute();
        return readResult.getValueRanges().get(0).getValues();

    }

    public List<Category> convertDataFromGoogleSheetToDatabase(List<List<Object>> values) {

        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            Category category = Category.builder()
                    .id(Long.parseLong(values.get(i).get(0).toString()))
                    .name(values.get(i).get(1).toString())
                    .slug(values.get(i).get(2).toString())
                    .build();

            categories.add(category);

        }

        return categories;
    }

    public void batchInsertProduct(List<Category> categories) {
        String sql = "INSERT INTO category (id,name, slug) VALUES (?,?,?)";
        int[] updateCounts = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Category c = categories.get(i);

                ps.setLong(1, c.getId());
                ps.setString(2, c.getName());
                ps.setString(3, c.getSlug());
            }

            @Override
            public int getBatchSize() {
                return categories.size();
            }
        });
    }

}

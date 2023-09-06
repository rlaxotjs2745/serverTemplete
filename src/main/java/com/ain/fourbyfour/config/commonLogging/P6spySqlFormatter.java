package com.ain.fourbyfour.config.commonLogging;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class P6spySqlFormatter implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        String formatSql = formatSql(category, sql);

        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy.MM.dd HH:mm:ss");

        return dateFormat.format(curDate) + " | " + "OperationTime : " + elapsed + "ms" + formatSql;
    }

    private String formatSql(String category, String sql) {

        String reqSql = sql;


        if(reqSql == null || reqSql.trim().equals("")){
            return reqSql;
        }

        if(Category.STATEMENT.getName().equals(category)){
            String tmpSql = reqSql.trim().toLowerCase();

            if(tmpSql.startsWith("create") || tmpSql.startsWith("alter") || tmpSql.startsWith("comment")){
                reqSql = FormatStyle.DDL.getFormatter().format(reqSql);
            } else {
                reqSql = FormatStyle.BASIC.getFormatter().format(reqSql);
            }

            reqSql = "\n" + reqSql + "\n";
        }

        return reqSql;
    }
}

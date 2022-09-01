package com.thecms.compenont;

import com.thecms.compenont.process.ForEachArticleElementTagProcess;
import com.thecms.compenont.process.ForEachColumnElementTagProcess;
import com.thecms.compenont.process.TheCmsForElementTagProcess;
import com.thecms.mapper.DialectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;


import java.util.HashSet;
import java.util.Set;

@Component
public class DialectProcess extends AbstractProcessorDialect {

    private static final String DIALECT_NAME = "TheCMS Dialect";
    private static final String DIALECT_PREFIX = "th";
    private static final int PRECEDENCE = 1000;

    @Autowired
    private DialectMapper dialectMapper;

    protected DialectProcess() {
        super(DIALECT_NAME, DIALECT_PREFIX, PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String s) {
        Set<IProcessor> processors = new HashSet<>();

        processors.add(new TheCmsForElementTagProcess(s,dialectMapper));
        processors.add(new ForEachArticleElementTagProcess(s,dialectMapper));
        processors.add(new ForEachColumnElementTagProcess(s,dialectMapper));

        return processors;
    }
}

package com.thecms.compenont.process;

import com.thecms.entity.ColumnEntity;
import com.thecms.mapper.DialectMapper;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;

public class ForEachColumnElementTagProcess extends AbstractElementTagProcessor {

    private static final String ELEMENT_NAME = "column";
    private static final int PRECEDENCE = 1000;
    private final DialectMapper dialectMapper;


    public ForEachColumnElementTagProcess(String dialectPrefix, DialectMapper dialectMapper) {
        super(TemplateMode.HTML, dialectPrefix, ELEMENT_NAME, true, null, false, PRECEDENCE);
        this.dialectMapper = dialectMapper;
    }

    @Override
    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag iProcessableElementTag, IElementTagStructureHandler iElementTagStructureHandler) {
        String name = iProcessableElementTag.getAttributeValue("name");

        List<ColumnEntity> column = dialectMapper.getColumn();
        iElementTagStructureHandler.iterateElement(name,null,column);

    }
}

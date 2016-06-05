package com.ftorterolo.SemDasboard.views;

import com.vaadin.ui.*;
import com.vaadin.ui.Layout.AlignmentHandler;

public class RootGridLayoutBase {

    private boolean constructor;
    protected final GridLayout rootGridLayout = new GridLayout();
    protected final VerticalLayout vL001 = new VerticalLayout();
    protected final HorizontalLayout hL001 = new HorizontalLayout();
    protected final VerticalLayout vL011 = new VerticalLayout();
    protected final VerticalLayout vL012 = new VerticalLayout();
    protected final VerticalLayout vL013 = new VerticalLayout();
    protected final HorizontalLayout hL002 = new HorizontalLayout();
    protected final VerticalLayout vL021 = new VerticalLayout();
    protected final VerticalLayout vL022 = new VerticalLayout();
    protected final VerticalLayout vL023 = new VerticalLayout();
    protected final HorizontalLayout hL003 = new HorizontalLayout();
    protected final VerticalLayout vL031 = new VerticalLayout();
    protected final VerticalLayout vL032 = new VerticalLayout();
    protected final VerticalLayout vL033 = new VerticalLayout();
    protected final Label labelD001 = new Label("D001:");
    protected final Label labelD002 = new Label("D002:");
    protected final Label labelD003 = new Label("D003:");
    protected final Label labelD004 = new Label("D004:");

    public RootGridLayoutBase() {
        constructor = true;
        reset();
        constructor = false;
    }

    public synchronized void reset() {
        rootGridLayout.removeAllComponents();
        hL001.addComponent(vL011);
        hL001.addComponent(vL012);
        hL001.addComponent(vL013);
        hL002.addComponent(vL021);
        hL002.addComponent(vL022);
        hL002.addComponent(vL023);
        hL003.addComponent(vL031);
        hL003.addComponent(vL032);
        hL003.addComponent(vL033);
        vL001.addComponent(hL001);
        vL001.addComponent(hL002);
        vL001.addComponent(hL003);
        rootGridLayout.addComponent(vL001);
        initRootGridLayout();
        initVL001();
        initHL003();
        initVL010();
        initVL009();
        initVL008();
        initHL002();
        initVL006();
        initVL007();
        initVL005();
        initHL001();
        initVL004();
        initVL003();
        initVL002();
    }

    public void setLabelD001 (String value) {
        labelD001.setValue(value);
    }
    public void setLabelD002 (String value) {
        labelD002.setValue(value);
    }
    public void setLabelD003 (String value) {
        labelD003.setValue(value);
    }
    public void setLabelD004 (String value) {
        labelD004.setValue(value);
    }


    public final GridLayout getRootComponent() {
        return rootGridLayout;
    }

    protected void initVL002() {
        vL011.addStyleName("");
        vL011.setEnabled(true);
        vL011.setVisible(true);
//        vL011.setSpacing(true);
        vL011.setMargin(false);
        vL011.setImmediate(false);
        vL011.setWidth("100.0%");
        vL011.setHeight("100.0%");

        labelD001.addStyleName("d001 salida");
        vL011.addComponent(labelD001);

        labelD002.addStyleName("d002 entrada");
        vL011.addComponent(labelD002);

        labelD003.addStyleName("d003 salida");
        vL011.addComponent(labelD003);

        labelD004.addStyleName("d004 entrada");
        vL011.addComponent(labelD004);

//        vL011.addStyleName("salida");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL011.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL011, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL011.getParent();
        parentAlignmentHandler.setComponentAlignment(vL011, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL011() {
        return vL011;
    }

    protected void initVL003() {
        vL012.addStyleName("");
        vL012.setEnabled(true);
        vL012.setVisible(true);
//        vL012.setSpacing(true);
        vL012.setMargin(false);
        vL012.setImmediate(false);
        vL012.setWidth("100.0%");
        vL012.setHeight("100.0%");
//        vL012.addStyleName("calle");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL012.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL012, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL012.getParent();
        parentAlignmentHandler.setComponentAlignment(vL012, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL012() {
        return vL012;
    }

    protected void initVL004() {
        vL013.addStyleName("");
        vL013.setEnabled(true);
        vL013.setVisible(true);
//        vL013.setSpacing(true);
        vL013.setMargin(false);
        vL013.setImmediate(false);
        vL013.setWidth("100.0%");
        vL013.setHeight("100.0%");
//        vL013.addStyleName("salida");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL013.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL013, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL013.getParent();
        parentAlignmentHandler.setComponentAlignment(vL013, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL013() {
        return vL013;
    }

    protected void initHL001() {
        hL001.addStyleName("");
        hL001.setEnabled(true);
        hL001.setVisible(true);
//        hL001.setSpacing(true);
        hL001.setMargin(false);
        hL001.setImmediate(false);
        hL001.setWidth("100.0%");
        hL001.setHeight("100.0%");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) hL001.getParent();
        parentAbstractOrderedLayout.setExpandRatio(hL001, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) hL001.getParent();
        parentAlignmentHandler.setComponentAlignment(hL001, Alignment.TOP_LEFT);
    }

    public final HorizontalLayout gethL001() {
        return hL001;
    }

    protected void initVL005() {
        vL021.addStyleName("");
        vL021.setEnabled(true);
        vL021.setVisible(true);
//        vL021.setSpacing(true);
        vL021.setMargin(false);
        vL021.setImmediate(false);
        vL021.setWidth("100.0%");
        vL021.setHeight("100.0%");
//        vL021.addStyleName("calleH");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL021.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL021, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL021.getParent();
        parentAlignmentHandler.setComponentAlignment(vL021, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL021() {
        return vL021;
    }

    protected void initVL007() {
        vL022.addStyleName("");
        vL022.setEnabled(true);
        vL022.setVisible(true);
//        vL022.setSpacing(true);
        vL022.setMargin(false);
        vL022.setImmediate(false);
        vL022.setWidth("100.0%");
        vL022.setHeight("100.0%");
//        vL022.addStyleName("calleT");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL022.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL022, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL022.getParent();
        parentAlignmentHandler.setComponentAlignment(vL022, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL022() {
        return vL022;
    }

    protected void initVL006() {
        vL023.addStyleName("");
        vL023.setEnabled(true);
        vL023.setVisible(true);
//        vL023.setSpacing(true);
        vL023.setMargin(false);
        vL023.setImmediate(false);
        vL023.setWidth("100.0%");
        vL023.setHeight("100.0%");



//        vL023.addStyleName("calleH");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL023.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL023, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL023.getParent();
        parentAlignmentHandler.setComponentAlignment(vL023, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL023() {
        return vL023;
    }

    protected void initHL002() {
        hL002.addStyleName("");
        hL002.setEnabled(true);
        hL002.setVisible(true);
//        hL002.setSpacing(true);
        hL002.setMargin(false);
        hL002.setImmediate(false);
        hL002.setWidth("100.0%");
        hL002.setHeight("100.0%");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) hL002.getParent();
        parentAbstractOrderedLayout.setExpandRatio(hL002, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) hL002.getParent();
        parentAlignmentHandler.setComponentAlignment(hL002, Alignment.TOP_LEFT);
    }

    public final HorizontalLayout gethL002() {
        return hL002;
    }

    protected void initVL008() {
        vL031.addStyleName("");
        vL031.setEnabled(true);
        vL031.setVisible(true);
//        vL031.setSpacing(true);
        vL031.setMargin(false);
        vL031.setImmediate(false);
        vL031.setWidth("100.0%");
        vL031.setHeight("100.0%");
//        vL031.addStyleName("salida");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL031.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL031, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL031.getParent();
        parentAlignmentHandler.setComponentAlignment(vL031, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL031() {
        return vL031;
    }

    protected void initVL009() {
        vL032.addStyleName("");
        vL032.setEnabled(true);
        vL032.setVisible(true);
//        vL032.setSpacing(true);
        vL032.setMargin(false);
        vL032.setImmediate(false);
        vL032.setWidth("100.0%");
        vL032.setHeight("100.0%");

//        vL032.addStyleName("calle");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL032.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL032, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL032.getParent();
        parentAlignmentHandler.setComponentAlignment(vL032, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL032() {
        return vL032;
    }

    protected void initVL010() {
        vL033.addStyleName("");
        vL033.setEnabled(true);
        vL033.setVisible(true);
//        vL033.setSpacing(true);
        vL033.setMargin(false);
        vL033.setImmediate(false);
        vL033.setWidth("100.0%");
        vL033.setHeight("100.0%");



//        vL033.addStyleName("salida");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL033.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL033, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL033.getParent();
        parentAlignmentHandler.setComponentAlignment(vL033, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL033() {
        return vL033;
    }

    protected void initHL003() {
        hL003.addStyleName("");
        hL003.setEnabled(true);
        hL003.setVisible(true);
//        hL003.setSpacing(true);
        hL003.setMargin(false);
        hL003.setImmediate(false);
        hL003.setWidth("100.0%");
        hL003.setHeight("100.0%");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) hL003.getParent();
        parentAbstractOrderedLayout.setExpandRatio(hL003, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) hL003.getParent();
        parentAlignmentHandler.setComponentAlignment(hL003, Alignment.TOP_LEFT);
    }

    public final HorizontalLayout gethL003() {
        return hL003;
    }

    protected void initVL001() {
        vL001.addStyleName("calleT");
        vL001.setEnabled(true);
        vL001.setVisible(true);
//        vL001.setSpacing(true);
        vL001.setMargin(false);
        vL001.setImmediate(false);
        vL001.setWidth("100.0%");
        vL001.setHeight("100.0%");
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL001.getParent();
        parentAlignmentHandler.setComponentAlignment(vL001, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL001() {
        return vL001;
    }

    protected void initRootGridLayout() {
        rootGridLayout.setColumns(1);
        rootGridLayout.addStyleName("");
        rootGridLayout.setEnabled(true);
        rootGridLayout.setVisible(true);
//        rootGridLayout.setSpacing(true);
        rootGridLayout.setMargin(false);
        rootGridLayout.setImmediate(false);
        rootGridLayout.setWidth("100.0%");
        rootGridLayout.setHeight("100.0%");
    }

    public final GridLayout getRootGridLayout() {
        return rootGridLayout;
    }
}

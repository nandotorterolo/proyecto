package com.ftorterolo.SemDasboard.views;

import com.vaadin.ui.*;
import com.vaadin.ui.Layout.AlignmentHandler;

public class RootGridLayoutBase {

    private boolean constructor;
    protected final GridLayout rootGridLayout = new GridLayout();
    protected final VerticalLayout vL001 = new VerticalLayout();
    protected final HorizontalLayout hL001 = new HorizontalLayout();
    protected final VerticalLayout vL002 = new VerticalLayout();
    protected final VerticalLayout vL003 = new VerticalLayout();
    protected final VerticalLayout vL004 = new VerticalLayout();
    protected final HorizontalLayout hL002 = new HorizontalLayout();
    protected final VerticalLayout vL005 = new VerticalLayout();
    protected final VerticalLayout vL007 = new VerticalLayout();
    protected final VerticalLayout vL006 = new VerticalLayout();
    protected final HorizontalLayout hL003 = new HorizontalLayout();
    protected final VerticalLayout vL008 = new VerticalLayout();
    protected final VerticalLayout vL009 = new VerticalLayout();
    protected final VerticalLayout vL010 = new VerticalLayout();
    protected final Label labelD001 = new Label("TEST");

    public RootGridLayoutBase() {
        constructor = true;
        reset();
        constructor = false;
    }

    public synchronized void reset() {
        rootGridLayout.removeAllComponents();
        hL001.addComponent(vL002);
        hL001.addComponent(vL003);
        hL001.addComponent(vL004);
        vL001.addComponent(hL001);
        hL002.addComponent(vL005);
        hL002.addComponent(vL007);
        hL002.addComponent(vL006);
        vL001.addComponent(hL002);
        hL003.addComponent(vL008);
        hL003.addComponent(vL009);
        hL003.addComponent(vL010);
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

    public final GridLayout getRootComponent() {
        return rootGridLayout;
    }

    protected void initVL002() {
        vL002.addStyleName("");
        vL002.setEnabled(true);
        vL002.setVisible(true);
//        vL002.setSpacing(true);
        vL002.setMargin(false);
        vL002.setImmediate(false);
        vL002.setWidth("100.0%");
        vL002.setHeight("100.0%");
//        vL002.addStyleName("salida");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL002.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL002, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL002.getParent();
        parentAlignmentHandler.setComponentAlignment(vL002, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL002() {
        return vL002;
    }

    protected void initVL003() {
        vL003.addStyleName("");
        vL003.setEnabled(true);
        vL003.setVisible(true);
//        vL003.setSpacing(true);
        vL003.setMargin(false);
        vL003.setImmediate(false);
        vL003.setWidth("100.0%");
        vL003.setHeight("100.0%");

        vL003.addComponent(labelD001);
        vL003.setComponentAlignment(labelD001,Alignment.BOTTOM_RIGHT);
//        vL003.addStyleName("calle");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL003.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL003, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL003.getParent();
        parentAlignmentHandler.setComponentAlignment(vL003, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL003() {
        return vL003;
    }

    protected void initVL004() {
        vL004.addStyleName("");
        vL004.setEnabled(true);
        vL004.setVisible(true);
//        vL004.setSpacing(true);
        vL004.setMargin(false);
        vL004.setImmediate(false);
        vL004.setWidth("100.0%");
        vL004.setHeight("100.0%");
//        vL004.addStyleName("salida");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL004.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL004, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL004.getParent();
        parentAlignmentHandler.setComponentAlignment(vL004, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL004() {
        return vL004;
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
        vL005.addStyleName("");
        vL005.setEnabled(true);
        vL005.setVisible(true);
//        vL005.setSpacing(true);
        vL005.setMargin(false);
        vL005.setImmediate(false);
        vL005.setWidth("100.0%");
        vL005.setHeight("100.0%");
//        vL005.addStyleName("calleH");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL005.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL005, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL005.getParent();
        parentAlignmentHandler.setComponentAlignment(vL005, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL005() {
        return vL005;
    }

    protected void initVL007() {
        vL007.addStyleName("");
        vL007.setEnabled(true);
        vL007.setVisible(true);
//        vL007.setSpacing(true);
        vL007.setMargin(false);
        vL007.setImmediate(false);
        vL007.setWidth("100.0%");
        vL007.setHeight("100.0%");
//        vL007.addStyleName("calleT");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL007.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL007, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL007.getParent();
        parentAlignmentHandler.setComponentAlignment(vL007, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL007() {
        return vL007;
    }

    protected void initVL006() {
        vL006.addStyleName("");
        vL006.setEnabled(true);
        vL006.setVisible(true);
//        vL006.setSpacing(true);
        vL006.setMargin(false);
        vL006.setImmediate(false);
        vL006.setWidth("100.0%");
        vL006.setHeight("100.0%");
//        vL006.addStyleName("calleH");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL006.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL006, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL006.getParent();
        parentAlignmentHandler.setComponentAlignment(vL006, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL006() {
        return vL006;
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
        vL008.addStyleName("");
        vL008.setEnabled(true);
        vL008.setVisible(true);
//        vL008.setSpacing(true);
        vL008.setMargin(false);
        vL008.setImmediate(false);
        vL008.setWidth("100.0%");
        vL008.setHeight("100.0%");
//        vL008.addStyleName("salida");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL008.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL008, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL008.getParent();
        parentAlignmentHandler.setComponentAlignment(vL008, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL008() {
        return vL008;
    }

    protected void initVL009() {
        vL009.addStyleName("");
        vL009.setEnabled(true);
        vL009.setVisible(true);
//        vL009.setSpacing(true);
        vL009.setMargin(false);
        vL009.setImmediate(false);
        vL009.setWidth("100.0%");
        vL009.setHeight("100.0%");
//        vL009.addStyleName("calle");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL009.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL009, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL009.getParent();
        parentAlignmentHandler.setComponentAlignment(vL009, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL009() {
        return vL009;
    }

    protected void initVL010() {
        vL010.addStyleName("");
        vL010.setEnabled(true);
        vL010.setVisible(true);
//        vL010.setSpacing(true);
        vL010.setMargin(false);
        vL010.setImmediate(false);
        vL010.setWidth("100.0%");
        vL010.setHeight("100.0%");
//        vL010.addStyleName("salida");
        AbstractOrderedLayout parentAbstractOrderedLayout = (AbstractOrderedLayout) vL010.getParent();
        parentAbstractOrderedLayout.setExpandRatio(vL010, Double.valueOf(0.0).floatValue());
        AlignmentHandler parentAlignmentHandler = (AlignmentHandler) vL010.getParent();
        parentAlignmentHandler.setComponentAlignment(vL010, Alignment.TOP_LEFT);
    }

    public final VerticalLayout getvL010() {
        return vL010;
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

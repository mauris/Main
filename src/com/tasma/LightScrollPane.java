package com.tasma;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class LightScrollPane extends JComponent{
	
	private static final int SCROLL_BAR_ALPHA_ROLLOVER = 150;
    private static final int SCROLL_BAR_ALPHA = 100;
    private static final int THUMB_BORDER_SIZE = 2;
    private static final int THUMB_SIZE = 8;
    private static final Color THUMB_COLOR = Color.BLACK;

    private final JScrollPane scrollPane;
    private final JScrollBar verticalScrollBar;
    private final JScrollBar horizontalScrollBar;
    
    public LightScrollPane(JComponent component) {
        scrollPane = new JScrollPane(component);
        verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setVisible(false);
        verticalScrollBar.setOpaque(false);
        verticalScrollBar.setUI(new MyScrollBarUI());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayer(verticalScrollBar, JLayeredPane.PALETTE_LAYER);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setLayout(new ScrollPaneLayout() {
            @Override
            public void layoutContainer(Container parent) {
                viewport.setBounds(0, 0, getWidth(), getHeight());
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        displayScrollBarsIfNecessary(viewport);
                    }
                });
            }
        });
        
        layeredPane.add(verticalScrollBar);
        layeredPane.add(scrollPane);

        setLayout(new BorderLayout() {
            @Override
            public void layoutContainer(Container target) {
                super.layoutContainer(target);
                int width = getWidth();
                int height = getHeight();
                scrollPane.setBounds(0, 0, width, height);

                int scrollBarSize = 12;
                int cornerOffset = verticalScrollBar.isVisible() &&
                        horizontalScrollBar.isVisible() ? scrollBarSize : 0;
                if (verticalScrollBar.isVisible()) {
                    verticalScrollBar.setBounds(width - scrollBarSize, 0,
                            scrollBarSize, height - cornerOffset);
                }
                if (horizontalScrollBar.isVisible()) {
                    horizontalScrollBar.setBounds(0, height - scrollBarSize,
                            width - cornerOffset, scrollBarSize);
                }
            }
        });
}

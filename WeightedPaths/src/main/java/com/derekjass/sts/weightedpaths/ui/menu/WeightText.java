package com.derekjass.sts.weightedpaths.ui.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.derekjass.sts.weightedpaths.WeightedPaths;
import com.derekjass.sts.weightedpaths.ui.Renderable;
import com.megacrit.cardcrawl.helpers.FontHelper;

class WeightText implements Renderable {

    private final String nodeType;
    private final float x, y;

    WeightText(float x, float y, String nodeType) {
        this.nodeType = nodeType;
        this.x = x;
        this.y = y;
    }

    public void render(SpriteBatch sb) {
        String weight = String.format("%.1f", WeightedPaths.weights.get(nodeType));
        FontHelper.renderFontCentered(sb, FontHelper.tipBodyFont, weight,
                x, y + WeightArrow.height / 2, WeightsMenu.FONT_COLOR);
    }
}

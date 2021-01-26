package com.derekjass.sts.weightedpaths.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.derekjass.sts.weightedpaths.WeightedPaths;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.map.MapRoomNode;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class WeightRenderPatches {

    private static final Color WEIGHT_COLOR = new Color(0x00_00_00_58);
    private static BitmapFont font;

    private static void drawNodeValue(MapRoomNode room, SpriteBatch sb) {
        if (WeightedPaths.roomValues.containsKey(room)) {
            double value = WeightedPaths.roomValues.get(room);
            FontHelper.renderFont(sb, font, String.format("%.1f", value), room.hb.cX + 25, room.hb.cY, WEIGHT_COLOR);
        }
    }

    @SpirePatch(clz = MapRoomNode.class, method = "render")
    public static class PostMapRoomNodeRenderPatch {

        @SpirePostfixPatch
        public static void onMapRoomNodeRender(MapRoomNode room, SpriteBatch sb) {
            drawNodeValue(room, sb);
        }
    }

    @SpirePatch(clz = FontHelper.class, method = "initialize")
    public static class PreTipBodyFontCreationPatch {

        @SpireInsertPatch(locator = Locator.class)
        public static void onFontCreation() {
            font = FontHelper.prepFont(18.0f, false);
        }

        private static class Locator extends SpireInsertLocator {

            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(FontHelper.class, "tipBodyFont");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}

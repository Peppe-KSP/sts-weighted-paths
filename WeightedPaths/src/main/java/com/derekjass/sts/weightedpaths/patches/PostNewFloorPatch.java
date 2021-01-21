package com.derekjass.sts.weightedpaths.patches;

import com.derekjass.sts.weightedpaths.WeightedPaths;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpirePatch(clz = AbstractDungeon.class, method = "setCurrMapNode")
public class PostNewFloorPatch {

    private static final Logger logger = LogManager.getLogger(PostNewFloorPatch.class.getName());

    @SpirePostfixPatch
    public static void onNewFloor(MapRoomNode room) {
        WeightedPaths.regeneratePaths();
        WeightedPaths.logTopPaths(5, logger);
    }
}

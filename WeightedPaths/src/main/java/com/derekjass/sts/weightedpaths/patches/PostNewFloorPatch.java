package com.derekjass.sts.weightedpaths.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;

@SpirePatch(clz=AbstractDungeon.class, method="setCurrMapNode")
public class PostNewFloorPatch {

    @SpirePostfixPatch
    public static void onNewFloor(MapRoomNode room) {
        System.out.println("Path chosen: " + room);
    }
}

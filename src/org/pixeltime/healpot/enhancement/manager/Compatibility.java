package org.pixeltime.healpot.enhancement.manager;

import org.bukkit.Bukkit;
import org.pixeltime.healpot.enhancement.Main;
import org.pixeltime.healpot.enhancement.interfaces.GlowItem;
import org.pixeltime.healpot.enhancement.interfaces.PlaySound;
import org.pixeltime.healpot.enhancement.interfaces.SpawnFirework;
import org.pixeltime.healpot.enhancement.manager.modular.GlowItem_Unsafe;
import org.pixeltime.healpot.enhancement.manager.modular.PlaySound_Safe;
import org.pixeltime.healpot.enhancement.manager.modular.SpawnFirework_Safe;
import org.pixeltime.healpot.enhancement.util.Reflection_V2;

public class Compatibility {
    public static GlowItem glow;
    public static PlaySound playsound;
    public static SpawnFirework spawnFirework;


    /**
     * Finds the right version for item glower.
     * 
     * @return
     */
    public boolean setupGlow() {
        Main.getMain().getLogger().info("Your server is running version "
            + Reflection_V2.getVERSION());
        /*
         * if (version.equals("v1_8_R3")) {
         * glow = new GlowItem_NBT();
         * }
         * else {
         * glow = new GlowItem_Unsafe();
         * }
         */
        glow = new GlowItem_Unsafe();
        return glow != null;
    }


    /**
     * Finds the right version for play sound.
     * 
     * @return
     */
    public boolean setupSound() {
        playsound = new PlaySound_Safe();
        return playsound != null;
    }


    /**
     * Finds the right version to spawn fireworks.
     * 
     * @return
     */
    public boolean setupFirework() {
        spawnFirework = new SpawnFirework_Safe();
        return spawnFirework != null;
    }

}

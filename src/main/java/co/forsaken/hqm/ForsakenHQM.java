package co.forsaken.hqm;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "ForsakenHQM", name = "Forsaken Server HQM Addon", version = "1.0.0") public class ForsakenHQM {
  @Mod.Instance("ForsakenHQM") public static ForsakenHQM instance;

  @Mod.EventHandler public void serverStarting(FMLServerStartingEvent event) {
    event.registerServerCommand(new ForsakenCommandHandler());
    QuestingData.
  }
}

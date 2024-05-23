package millaav.vajrapro.common.registry;

import ic2.core.AdvRecipe;
import ic2.core.Ic2Items;
import millaav.vajrapro.common.Item.EnumUpgradeType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipeRegistry {

    public static void init(){
        //Мотыга
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.HOE.ordinal()), "ACA"," E ", "ACA",
                'A', Ic2Items.advancedAlloy, 'C', new ItemStack(Ic2Items.chargingREBattery.getItem(),1, 32767),
                'E', new ItemStack(Ic2Items.electricHoe.getItem(),1, 32767));

        //Авто-переплавка
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.AUTOMELTING.ordinal()), "FAF", "OPO", "FAF",
                'F', Items.blaze_powder, 'A', new ItemStack(Ic2Items.energyCrystal.getItem(), 1,32767),
                'O', Ic2Items.denseplateobsidian,'P', new ItemStack(Ic2Items.electroFurnace.getItem(),1, 2));

        //SkillTouch
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.SILKTOUCH.ordinal()), "ABA","BFB", "ABA",
                'A', new ItemStack(Ic2Items.reactorHeatSwitchDiamond.getItem(), 1, 32767),
                'B', Ic2Items.diamondblockcuttingblade, 'F', new ItemStack(ItemRegistry.coolingcore, 1));

        //Удача 1 уровня 3
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.FORTUNA.ordinal()),"IPI","EUE","IPI",
                'I', Ic2Items.iridiumPlate, 'P',Ic2Items.advancedCircuit, 'E', new ItemStack(Ic2Items.energyCrystal.getItem(),1,32767),
                'U', new ItemStack(Ic2Items.uuMatterCell.getItem(),1, 3));

        //Удача 2 уровня 5
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.FORTUNA1.ordinal()),"IPI","FDF","IPI",
                'I', Ic2Items.iridiumPlate, 'P', new ItemStack(ItemRegistry.goldplatedenergycrystal, 1, 32767),
                'D', new ItemStack(Ic2Items.iridiumDrill.getItem(),1,32767),
                'F', new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.FORTUNA.ordinal()));

        //Удача 3 уровня 10
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.FORTUNA2.ordinal()),"FPF","LRL","FPF",
                'F', new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.FORTUNA1.ordinal()),
                'P', new ItemStack(ItemRegistry.coolingcore, 1),
                'L', new ItemStack(Ic2Items.lapotronCrystal.getItem(),1,32767), 'R', Ic2Items.RTGPellets);

        //Глубина копания 1 уровня
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.DEPTH.ordinal()), "EPE","HFH","EPE",
                'E', new ItemStack(Ic2Items.energyCrystal.getItem(),1,32767), 'P', Ic2Items.advancedCircuit,'F',Ic2Items.fluidEjectorUpgrade,
                'H', new ItemStack(Ic2Items.reactorVentDiamond.getItem()));

        //Глубина копания 2 уровня
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.DEPTH1.ordinal()), "CPC","MDM","CPC",
                'C', new ItemStack(Ic2Items.reactorCoolantSimple.getItem(),1,1), 'P', new ItemStack(ItemRegistry.superconductorcover, 1),
                'D',new ItemStack(Ic2Items.diamondDrill.getItem(),1,32767),
                'M', new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.DEPTH.ordinal()));

        //Глубина копания 3 уровня
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.DEPTH2.ordinal()), "MPM","CBC","MPM",
                'B', new ItemStack(ItemRegistry.coolingcore, 1), 'P', Ic2Items.advancedCircuit,'C', new ItemStack(Ic2Items.reactorCoolantSix.getItem(),1,1),
                'M', new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.DEPTH1.ordinal()));

        //Урон по радиусу
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.RANGE.ordinal()), "IAI","BMB","IAI",
                'A', new ItemStack(Items.golden_apple,1,1), 'I', Ic2Items.iridiumPlate,'B',new ItemStack(Ic2Items.chargingAdvBattery.getItem(),1, 32767),
                'M', new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.DAMAGE1.ordinal()));

        //Урон 1 уровня (21)
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.DAMAGE.ordinal()), "APA","ESE","APA",
                'A', Ic2Items.advancedAlloy, 'P', Ic2Items.advancedCircuit,'E',Items.emerald,
                'S', Items.diamond_sword);

        //Урон 2 уровня (61)
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.DAMAGE1.ordinal()), "EPE","MSM","EPE",
                'E', new ItemStack(Ic2Items.energyCrystal.getItem(),1,32767), 'P', Ic2Items.advancedCircuit,'S',new ItemStack(Ic2Items.nanoSaber.getItem(),1,32767),
                'M', new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.DAMAGE.ordinal()));

        //Урон 3 уровня (121)
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.DAMAGE2.ordinal()), "MAM","LNL","MAM",
                'A', Ic2Items.advancedCircuit, 'N', Items.nether_star,'L',new ItemStack(Ic2Items.lapotronCrystal.getItem(),1,32767),
                'M', new ItemStack(ItemRegistry.upgrade, 1, EnumUpgradeType.DAMAGE1.ordinal()));

        //Tool Station
        AdvRecipe.addAndRegister(new ItemStack(BlockRegistry.toolStation, 1), "CAC","BTB","CAC",
                'C', Ic2Items.carbonPlate, 'A', Ic2Items.advancedCircuit, 'T', new ItemStack(Ic2Items.sortingmachine.getItem(),1,8),
                'B', Ic2Items.machine);

        //Cooling Core
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.coolingcore, 1), "CHC","PIP","CHC",
                'C', new ItemStack(Ic2Items.reactorCoolantSix.getItem(),1,1), 'H', new ItemStack(Ic2Items.reactorHeatSwitchDiamond.getItem()),
                'P', Ic2Items.reactorPlatingHeat, 'I', Ic2Items.iridiumPlate);

        //Super Conductor Cover
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.superconductorcover, 3), "AIA", "CCC","AIA",
                'A', Ic2Items.advancedAlloy, 'I', Ic2Items.iridiumPlate, 'C', Ic2Items.carbonPlate);

        //Super Conductor
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.superconductor, 1), "SSS","GCG","SSS",
                'S', new ItemStack(ItemRegistry.superconductorcover, 1),'G',Ic2Items.denseplategold,
                'C', new ItemStack(Ic2Items.glassFiberCableItem.getItem(), 1,9));

        //Magnetron
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.magnetron, 1), "ICI", "CSC","ICI",
                'I', Ic2Items.plateiron, 'C', Ic2Items.platecopper, 'S', new ItemStack(ItemRegistry.superconductor,1));

        //Vajra Core
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.vajracore, 1), " M ","ITI", "SCS",
                'I', Ic2Items.iridiumPlate, 'T', new ItemStack(Ic2Items.teslaCoil.getItem(), 1, 1),
                'M', new ItemStack(ItemRegistry.magnetron,1), 'S', new ItemStack(ItemRegistry.superconductor,1),
                'C', new ItemStack(ItemRegistry.coolingcore, 1));

        //Vajra
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.vajraС, 1),"IEI","CVC","ALA",
                'I', Ic2Items.plateiron, 'C', Ic2Items.carbonPlate, 'A', Ic2Items.advancedAlloy,
                'E', new ItemStack(Ic2Items.energyCrystal.getItem(), 1,32767),
                'V', new ItemStack(ItemRegistry.vajracore, 1), 'L', new ItemStack(Ic2Items.lapotronCrystal.getItem(),1,32767));


        //УЛУЧШЕНИE НА ВАДЖРУ 1 УРОВНЯ
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.tier1upgrade, 1), "ILI", "ACA", "ILI",
                'I', Ic2Items.iridiumPlate, 'A', Ic2Items.advancedAlloy, 'L',
                new ItemStack(ItemRegistry.goldplatedenergycrystal,1,32767), 'C', new ItemStack(ItemRegistry.vajracore1, 1));

        //УЛУЧШЕНИE НА ВАДЖРУ 2 УРОВНЯ
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.tier2upgrade, 1), "LUL", "CVC", "LUL",
                'U', new ItemStack(ItemRegistry.tier1upgrade, 1), 'L', new ItemStack(Ic2Items.lapotronCrystal.getItem(),1,32767),
                'C', new ItemStack(ItemRegistry.coolingcore, 1), 'V', new ItemStack(ItemRegistry.vajracore2, 1));

      //Желтый кристалл
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.goldplatedenergycrystal, 1, 32767), "GPG", "GEG","GPG",
                'G', Ic2Items.goldDust, 'P', Ic2Items.electronicCircuit,
                'E', new ItemStack(Ic2Items.energyCrystal.getItem(), 1, 32767));

        //Magnetron 2 lvl
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.magnetron1, 1), "PCP", "MLM", "PCP",
                'P', Ic2Items.advancedCircuit, 'C', Ic2Items.carbonPlate, 'M', new ItemStack(ItemRegistry.magnetron, 1),
                'L', new ItemStack(Ic2Items.lapotronCrystal.getItem(), 1, 32767));

        //Magnetron 3 lvl
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.magnetron2, 1), "RSR","MCM", "RSR",
                'R', Ic2Items.reactorPlatingExplosive, 'S', new ItemStack(ItemRegistry.superconductor, 1),
                'M', new ItemStack(ItemRegistry.magnetron1, 1), 'C', new ItemStack(ItemRegistry.coolingcore, 1));

        //Vajra Core 2 lvl
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.vajracore1, 1),"CMC", "PFP","SVS",
                'C', new ItemStack(Ic2Items.reactorCoolantSix.getItem(),1,1),
                'F', new ItemStack(Ic2Items.mfeUnit.getItem(),1,32767),
                'M', new ItemStack(ItemRegistry.magnetron1,1), 'P', Ic2Items.reactorPlatingExplosive,
                'S', new ItemStack(ItemRegistry.superconductor, 1), 'V', new ItemStack(ItemRegistry.vajracore, 1));

        //Vajra Core 3 lvl
        AdvRecipe.addAndRegister(new ItemStack(ItemRegistry.vajracore2, 1),"CMC", "PFP","CVC",
                'C', new ItemStack(Ic2Items.reactorCoolantSix.getItem(),1,1),
                'M', new ItemStack(ItemRegistry.magnetron2,1), 'P', new ItemStack(ItemRegistry.coolingcore, 1),
                'F', new ItemStack(Ic2Items.mfsukit.getItem(), 1, 32767), 'V', new ItemStack(ItemRegistry.vajracore1, 1));
    }
}

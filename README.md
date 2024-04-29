[![](https://jitpack.io/v/j1sk1ss/ItemManager.PC.svg)](https://jitpack.io/#j1sk1ss/ItemManager.PC)

    public static ItemStack setLore(ItemStack item, String loreLine);
    public static ItemStack setName(ItemStack item, String name);
    public static void setMaterial(ItemStack itemStack, Material material);
    public static Material getMaterial(ItemStack item);
    public static List<String> getLoreLines(ItemStack itemStack);
    public static String getName(ItemStack itemStack);
    public static void giveItems(List<ItemStack> itemStacks, Player player);
    public static void giveItems(ItemStack item, Player player);
    public static void giveItemsWithoutLore(List<ItemStack> itemStacks, Player player);
    public static void giveItemsWithoutLore(ItemStack item, Player player);
    public static void takeItems(List<ItemStack> itemStacks, Player player);
    public static void takeItems(ItemStack itemStack, Player player);
    public static void setModelData(ItemStack itemStack, int modelData);
    public static int getModelData(ItemStack itemStack);
    public static void setDouble2Container(ItemStack itemStack, double value, String key);
    public static void setInteger2Container(ItemStack itemStack, int value, String key);
    public static double getDoubleFromContainer(ItemStack itemStack, String key);
    public static int getIntegerFromContainer(ItemStack itemStack, String key);
    public static void deleteKeyFromContainer(ItemStack itemStack, String key);

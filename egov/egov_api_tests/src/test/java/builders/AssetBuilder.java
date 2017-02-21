package builders;


import entities.Asset;

public class AssetBuilder {
    Asset asset =new Asset();

    public AssetBuilder()
    {
        asset.setCategory("C");
        asset.setName("sam");
        asset.setLocality("Block A");
        asset.setStreet("123Lane");
        asset.setZone("2");
        asset.setWard("B");
        asset.setBlock("5th");

    }

    public Asset build(){
        return asset;
    }
}
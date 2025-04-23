package org.example.Util;

public interface EncoderInterface {
    public int[] GetRegions(String postcode);
    public String SetFormat (int[][] regions);
    public int[] EncodePostcode(String postcode);

}

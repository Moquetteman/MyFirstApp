package com.example.massimo.myapplication;

public class Client {
    private Ice.Communicator ic;
    private Ice.ObjectPrx base;
    private serveur.ServeurIceMP3Prx ServeurIceMP3;
    private String[] artists;
    private String[] titles;

    public Client() {
        int status = 0;
        ic = null;
        try {
            ic = Ice.Util.initialize();
            base = ic.stringToProxy("SimplePrinter:default -h 178.62.148.22 -p 10000");
            ServeurIceMP3 = serveur.ServeurIceMP3PrxHelper.checkedCast(base);
        } catch (Ice.LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
    }

    public void AddFile(String title, String artist, String file)
    {
        ServeurIceMP3.ajoutfichier(title, artist, file);
    }

    public void Delete(String title, String artist)
    {
        ServeurIceMP3.suppression(title, artist);
    }

    public void TitlesList(String title)
    {
        titles = ServeurIceMP3.rechercheTitre(title);
    }

    public String[] getTitles()
    {
        return titles;
    }

    public void ArtistsList(String artist)
    {
        artists = ServeurIceMP3.rechercheAuteur(artist);
    }

    public String[] getArtists()
    {
        return artists;
    }

    public String dualSearch(String title, String artist)
    {
        return ServeurIceMP3.recherche(title,artist);
    }
    public void clientClose()
    {
        int status;
        if (ic != null) {
            try {
                ic.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
    }
}

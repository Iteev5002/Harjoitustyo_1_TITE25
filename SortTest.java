import java.util.*; // Tuodaan Java-kirjasto, jotta voidaan käyttää esimerkiksi Random-luokkaa.

public class SortTest { // Luodaan SortTest-niminen luokka.

    // Insertion sort eli lisäyslajittelu.
    // Metodi järjestää taulukon alkiot pienimmästä suurimpaan.
    public static void insertionSort(int[] data) {

        int n = data.length; // Tallennetaan taulukon alkioiden määrä muuttujaan n.

        // Käydään taulukkoa läpi toisesta alkiosta viimeiseen alkioon.
        for (int i = 1; i < n; ++i) {

            int key = data[i]; // Tallennetaan nykyinen alkio muuttujaan key.
            int j = i - 1; // Aloitetaan vertailu nykyistä alkiota edeltävästä alkiosta.

            // Siirretään key-muuttujaa suuremmat alkiot yhden paikan oikealle.
            // Jatketaan niin kauan kuin j on vähintään 0
            // ja edellinen alkio on suurempi kuin key.
            while (j >= 0 && data[j] > key) {

                data[j + 1] = data[j]; // Siirretään suurempi alkio yhden paikan oikealle.

                j = j - 1; // Siirrytään taulukossa yhden paikan vasemmalle.
            }

            data[j + 1] = key; // Asetetaan key oikealle paikalleen.
        }
    }

    public static void bubbleSort(int[] data) {
        int n = data.length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    

                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swapped = true;
                }
            }


            if (swapped == false)
                break;
        }
    }

    public static void shellSort(int[] data) {
        int n = data.length;
        for (int gap = n / 2; gap > 0; gap /=2) {
            for (int i = gap; i < n; i += 1) {
                int temp = data[i];
                int j;
                for (j = i; j >= gap && data[j - gap] > temp; j -= gap) {
                    data[j] = data[j - gap];
                }
            }
        }
    }

    public static void quickSort(int[] data) {
        quickSortHelper(data, 0, data.length - 1);
    }

    private static void quickSortHelper(int[] data, int low, int hight) {
        if (low < hight) {
            int pi = partition(data, low, hight);
            quickSortHelper(data, pi + 1, hight);
        }
    }

    private static int partition(int[] data, int low, int hight) {
        int pivot = data[hight];
        int i = (low - 1);
        for (int j = low; j < hight; j++) {
            if (data[j] <= pivot) {
                i++;
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        int temp = data[i + 1];
        data[i + 1] = data[hight];
        data[hight] = temp;
        return i + 1;
    }

    public static void mergeSort(int[] data) {
        if (data.length < 2) {
            return;
        }
        int mid = data.length / 2;
        int[] left = Arrays.copyOfRange(data, 0, mid);
        int[] right = Arrays.copyOfRange(data, mid, data.length);

        mergeSort(left);
        mergeSort(right);

        merge(data, left, right);
    }

    private static void merge(int[] data, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                data[k++] = left[i++];
            } else {
                data[k++] = right[j++];
            }
            
        }
        while (i < left.length) {
            data[k++] = left[i++];
        }
        while (j < right.length) {
            data[k++] = right[j++];  
        }
    }


    // Metodi luo uuden taulukon, jonka alkiot ovat nousevassa järjestyksessä.
    public static int[] createAscendingData(int n) {

        int[] data = new int[n]; // Luodaan uusi kokonaislukutaulukko, jossa on n alkiota.

        // Käydään taulukon kaikki indeksit läpi.
        for (int i = 0; i < data.length; i++) {

            data[i] = i; // Asetetaan alkion arvoksi sen indeksi.
        }

        return data; // Palautetaan valmis nousevassa järjestyksessä oleva taulukko.
    }

    // Metodi luo uuden taulukon, jonka alkiot ovat satunnaisessa järjestyksessä.
    public static int[] createRandomData(int n) {

        int[] data = new int[n]; // Luodaan uusi kokonaislukutaulukko, jossa on n alkiota.

        Random gen = new Random(); // Luodaan Random-olio satunnaislukujen arpomista varten.

        // Käydään taulukon kaikki indeksit läpi.
        for (int i = 0; i < data.length; i++) {

            data[i] = gen.nextInt(n); // Arvotaan taulukon alkioon satunnaisluku väliltä 0–n-1.
        }

        return data; // Palautetaan valmis satunnaisesti täytetty taulukko.
    }


    public static int[] createDescendingData(int n) {
        int[] data = new int [n];

        for (int i = 0; i < data.length; i++) {
            data[i] = data.length - 1 - i;
        }
        return data;

    }

    public static void main(String[] args) {

        int size = 50000; // Määritetään taulukon koko sopivaksi

        System.out.println("--- SATUNNAISELLA DATALLA ---");

        // 1. Luodaan taulukot valmiiksi ennen mittausta
        int[] dataIns = createRandomData(size);
        int[] dataBub = createRandomData(size);
        int[] dataArr = createRandomData(size);
        int[] dataShell = createRandomData(size);
        int[] dataQuick = createRandomData(size);
        int[] dataMerge = createRandomData(size);

        // Mittaukset
        long startInsertion = System.nanoTime();
        insertionSort(dataIns);
        long stopInsertion = System.nanoTime();

        long startBubble = System.nanoTime();
        bubbleSort(dataBub);
        long stopBubble = System.nanoTime();

        long startArrays = System.nanoTime();
        Arrays.sort(dataArr);
        long stopArrays = System.nanoTime();

        long startShell = System.nanoTime();
        shellSort(dataShell);
        long stopShell = System.nanoTime();

        long startQuick = System.nanoTime();
        quickSort(dataQuick);
        long stopQuick = System.nanoTime();

        long startMerge = System.nanoTime();
        mergeSort(dataMerge);
        long stopMerge = System.nanoTime();

        
        // Estetään optimointiongelmat lukemalla taulukkoa
        if (dataArr.length > 0) { int dummy = dataArr[0]; }

        System.out.println("Insertion sort (random): " + (stopInsertion - startInsertion) / 1000000000.0 + " s");
        System.out.println("Bubble sort (random): " + (stopBubble - startBubble) / 1000000000.0 + " s");
        System.out.println("Arrays.sort (random): " + (stopArrays - startArrays) / 1000000000.0 + " s");
        System.out.println("Shell sort (random): " + (stopShell - startShell) / 1000000000.0 + " s");
        System.out.println("Quick sort (random): " + (stopQuick - startQuick) / 1000000000.0 + " s");
        System.out.println("Merge sort (random): " + (stopMerge - startMerge) / 1000000000.0 + " s");
        System.out.println();


        System.out.println("--- TESTATAAN NOUSEVALLA DATALLA ---");

        int[] ascIns = createAscendingData(size);
        int[] ascBub = createAscendingData(size);
        int[] ascArr = createAscendingData(size);
        int[] ascShell = createAscendingData(size);
        int[] ascQuick = createAscendingData(size);
        int[] dataAscMerge = createAscendingData(size);


        long startAscIns = System.nanoTime();
        insertionSort(ascIns);
        long stopAscIns = System.nanoTime();

        long startAscBub = System.nanoTime();
        bubbleSort(ascBub);
        long stopAscBub = System.nanoTime();

        long startAscArr = System.nanoTime();
        Arrays.sort(ascArr);
        long stopAscArr = System.nanoTime();

        long startAscShell = System.nanoTime();
        shellSort(ascShell);
        long stopAscShell = System.nanoTime();

        long startAscQuick = System.nanoTime();
        quickSort(ascQuick);
        long stopAscQuick = System.nanoTime();

        long startAscMerge = System.nanoTime();
        mergeSort(dataAscMerge);
        long stopAscMerge = System.nanoTime();

        if (ascArr.length > 0) { int dummy = ascArr[0]; }

        System.out.println("Insertion sort (ascending): " + (stopAscIns - startAscIns) / 1000000000.0 + " s");
        System.out.println("Bubble sort (ascending):    " + (stopAscBub - startAscBub) / 1000000000.0 + " s");
        System.out.println("Arrays.sort (ascending):    " + (stopAscArr - startAscArr) / 1000000000.0 + " s");
        System.out.println("Shell sort (ascending):    " + (stopAscShell - startAscShell) / 1000000000.0 + " s");
        System.out.println("Quick sort (ascending):    " + (stopAscQuick - startAscQuick) / 1000000000.0 + " s");
        System.out.println("Merge sort (ascending):    " + (stopAscMerge - startAscMerge) / 1000000000.0 + " s");
        System.out.println();


        System.out.println("--- TESTATAAN LASKEVALLA DATALLA ---");

        int[] descIns = createDescendingData(size);
        int[] descBub = createDescendingData(size);
        int[] descArr = createDescendingData(size);
        int[] descShell = createDescendingData(size);
        int[] descQuick = createDescendingData(size);
        int[] descMerge = createDescendingData(size);


        long startDescIns = System.nanoTime();
        insertionSort(descIns);
        long stopDescIns = System.nanoTime();

        long startDescBub = System.nanoTime();
        bubbleSort(descBub);
        long stopDescBub = System.nanoTime();

        long startDescArr = System.nanoTime();
        Arrays.sort(descArr);
        long stopDescArr = System.nanoTime();

        long startDescShell = System.nanoTime();
        shellSort(descShell);
        long stopDescShell = System.nanoTime();

        long startDescQuick = System.nanoTime();
        quickSort(descQuick);
        long stopDescQuick = System.nanoTime();

        long startDescMerge = System.nanoTime();
        mergeSort(descMerge);
        long stopDescMerge = System.nanoTime();


        if (descArr.length > 0) { int dummy = descArr[0]; }

        System.out.println("Insertion sort (descending): " + (stopDescIns - startDescIns) / 1000000000.0 + " s");
        System.out.println("Bubble sort (descending): " + (stopDescBub - startDescBub) / 1000000000.0 + " s");
        System.out.println("Arrays.sort (descending): " + (stopDescArr - startDescArr) / 1000000000.0 + " s");
        System.out.println("Shell sort (descending): " + (stopDescShell - startDescShell) / 1000000000.0 + " s");
        System.out.println("Quick sort (descending): " + (stopDescQuick - startDescQuick) / 1000000000.0 + " s");
        System.out.println("Merge sort (descending): " + (stopDescMerge - startDescMerge) / 1000000000.0 + " s");
    }
}
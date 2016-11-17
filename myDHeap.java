

// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class myDHeap
{

    //Made binary
    int d = 2;
    /**
     * Construct the binary heap.
     */
    public myDHeap( )
    {
        this( DEFAULT_CAPACITY );
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public myDHeap( int capacity )
    {
        currentSize = 0;
        array = new int [ capacity + 1 ];
    }

    /**
     * Construct the binary heap given an array of items.
     */
    public myDHeap( int [] items, int var )
    {
        this.d = var;
        currentSize = items.length;
        array = new int[ ( currentSize + 2 ) * 11 / 10 ];

        int i = 1;
        for( int item : items )
            array[ i++ ] = item;
        buildHeap( );
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    public void insert(int x )
    {
        int exist = 0;
        for(int i = 1; i <= currentSize; i++){
            if(array[i] == x){
                System.out.printf("%d already exists in the heap\n", x);
                exist = 1;
            }
        }
        if (exist == 0){

            if( currentSize == array.length - 1 )
                enlargeArray( array.length * 2 + 1 );

            // Percolate up
            int hole = ++currentSize;
            for( array[ 0 ] = x; x <( array[ (hole + (d-2)) / d ] ); hole = (hole + (d-2))/d )
                array[ hole ] = array[ ((hole + (d-2))/d)];
            array[ hole ] = x;
        }


    }

    public void printOutput(){

        System.out.printf("Output: Heap (d= %d): ", d);

        for (int i = 1; i <= currentSize; ++i)
            System.out.printf("%d ", array[i]);

        System.out.printf("\n");
    }
    public void setD(int newD){
        this.d = newD;


    }

    private void enlargeArray( int newSize )
    {
        int [] old = array;
        array = new int[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public int findMin( )
    {
        //if( isEmpty( ) )
        //throw new UnderflowException( );
        return array[ 1 ];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */



    public int deleteMin( )
    {
        //if( isEmpty( ) )
        //  throw new UnderflowException( );

        int minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
public void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;      // Number of elements in heap
    private int [ ] array; // The heap array

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {
        int child;
        int tmp = array[ hole ];

        for( ; ((hole * d) - (d-2)) <= currentSize; hole = child )
        {
            child = ((hole * d) - (d-2));
            if( child != currentSize && array[ child + 1 ] < ( array[ child ] ))
                child++;
            if( array[ child ]< ( tmp ))
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
   public void remove(int key) {

        for(int i = 1; i <= currentSize; i++){
            if ( i == 1){
                if (key == array[i])
                    deleteMin();
            }

            else if (array[i] == key){
                array[ i ] = array[ currentSize-- ];
                percolateDown( i );


            }

            //System.out.printf("%d not found", key);

        }

    }


    public void changeValue(int o, int n){
        for(int i = 1; i <= currentSize; i++){
            if (array[i] == o){
                array[ i ] = n;
                percolateDown( i );


            }

            //System.out.printf("%d not found", key);

        }



    }

}

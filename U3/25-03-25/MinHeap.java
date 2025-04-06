interface Heap{
    void insert(int num);
    void delete(int num);
    
}
class MinHeap implements Heap{
    int[] nums;
    int size, maxSize;
    MinHeap(int maxSize){
        this.size=0;
        this.maxSize=maxSize;
        nums=new int[maxSize];
    }

    void swap(int[] nums,int idx, int jdx){
        nums[idx]=nums[idx]^nums[jdx];
        nums[jdx]=nums[idx]^nums[jdx];
        nums[idx]=nums[idx]^nums[jdx];
    }
    
    public void insert(int num){
        if(size==maxSize){
            System.out.println("Out of capacity!");
        }
        else{
            if(size==0){
                nums[0]=num;
                size++;
            }
            else {
                nums[size]=num;
                int idx=size,parent;
                while(idx>0){
                    parent=(size-1)/2;
                    if(nums[parent]>nums[idx]){
                        swap(nums, idx, parent);
                    }
                }
            }
        }
    }
    public void delete(int num){
        if(size==0){
            System.out.println("Heap is empty!");
        }
        
    }
}
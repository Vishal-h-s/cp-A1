#include<iostream>
class Shape{
    public:
    virtual void draw()=0;
};

class Circle: public Shape{
    public:
    void draw(){
       std::cout<< "Circle";
    }
};

void main(){
    
}
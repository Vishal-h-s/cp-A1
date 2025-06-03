#include <iostream>
#include <memory>
#include <algorithm>

using namespace std;

// interface 1
class MediaPlayer
{
public:
    virtual void play(const string &audioType, const string &filename) = 0;
    virtual ~MediaPlayer() {}
};

// interface 2
class AdvancedMediaPlayer
{
public:
    virtual void playVlc(const string &filename) = 0;
    virtual void playMp4(const string &filename) = 0;
    virtual ~AdvancedMediaPlayer() {}
};
// interface 2 implementing classes
class VlcPlayer : public AdvancedMediaPlayer
{
public:
    void playVlc(const string &filename) override
    {
        cout << "Playing vlc file. Name " << filename << endl;
    }
    void playMp4(const string &) override
    {
    }
};

class Mp4Player : public AdvancedMediaPlayer
{
public:
    void playVlc(const string &) override
    {
        
    }
    void playMp4(const string &filename) override
    {
        cout << "Playing Mp4 file. Name " << filename << endl;
    }
};


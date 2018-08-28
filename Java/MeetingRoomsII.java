/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
 */

import java.util.*;
public class MeetingRoomsII {
	public class Interval{
		int start;
		int end;
		Interval(){
			start = 0;
			end = 0;
		}
		Interval(int s, int e){
			start = s;
			end = e;
		}
	}
	
	/*
	 *  uses min heap, average time complexity is O(nlogn).
	 *  Use a min heap to track the minimum end time of merged intervals
	 *  
	 *  1. Sort the intervals by start time
	 *  2. Use a min heap to track the minimum end time of merged intervals, and offer(intervals[0])
	 *  3. iterate from index 1 to end, check if current meeting can use the available room
	 *  4. heap.size() is result
	 *  
	 *   it works because:
	 *   1. No two meetings can overlap, and no meetings can be removed. 
	 *   Therefore whenever two meetings overlap and there is no available meeting room, a new meeting room MUST be created
	 *   2. To minimize meeting rooms, we need to minimize the amount of time wasted (a.k.a. the time that the meeting room is not being used and is free). 
	 *   Therefore, we sort the meeting rooms by their end time (so that we can get the earliest available meeting room) 
	 *   and sort the upcoming meetings by their start time (so we can get the earliest meeting that can occupy that meeting room). 
	 *   If there is no meeting room available, then we must create a new one for that meeting.
	 *   
	 *   We maximize the use of the existing meeting rooms and therefore we minimize the number of meeting rooms created.
	 */
	public int minMeetingRooms(Interval[] intervals) {
	    if (intervals == null || intervals.length == 0)
	        return 0;
	        
	    // Sort the intervals by start time, ascending
	    Arrays.sort(intervals, new Comparator<Interval>() {
	        public int compare(Interval a, Interval b) { return a.start - b.start; }
	    });
	    // Arrays.sort(intervals, (a,b)->a.start-b.start);
	    
	    // Use a min heap to track the minimum end time of merged intervals
	    PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() { // !!
	        public int compare(Interval a, Interval b) { return a.end - b.end; }
	    });
	    // PriorityQueue<Interval> heap = new PriorityQueue<>((a,b)->a.end-b.end);
	    
	    // start with the first meeting, put it to a meeting room
	    heap.offer(intervals[0]);
	    
	    for (int i = 1; i < intervals.length; i++) {
	        // get the meeting room that finishes earliest
	        Interval interval = heap.poll();
	        
	        if (intervals[i].start >= interval.end) { // if the current meeting starts right after, there's no need for a new room, merge the interval
	            interval.end = intervals[i].end;
	        } else { // otherwise, this meeting needs a new room	            
	            heap.offer(intervals[i]);
	        }
	     
	        heap.offer(interval); // don't forget to put the meeting room back
	    }
	    
	    return heap.size();
	}
	
	
	
	/*
	 * According to greedy, you get one interval, then add the one right behind it. Then recursively deal with the rest.
	 * O(n^2) time complexity
	 */
	public int minMeetingRooms2222(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>(){
           public int compare(Interval o1, Interval o2){
               return o1.start - o2.start;
           } 
        });
        return helper(new ArrayList<>(Arrays.asList(intervals)));
    }
    
    private int helper(List<Interval> list){
        if(list.size() == 0)
            return 0;
        Interval pre = list.get(0);
        List<Interval> nextLi = new ArrayList<>();
        for(int i=1;i<list.size();i++){
            Interval inter = list.get(i);
            if(inter.start < pre.end){
                nextLi.add(inter);
            }else{
                pre = inter;
            }
        }
        return 1 + helper(nextLi);
    }
}

/*
Simulate event queue procession. Create event for each start and end of intervals. 
Then for start event, open one more room; for end event, close one meeting room. At the same time, update the most rooms that is required.

Be careful of events like [(end at 11), (start at 11)]. 
Put end before start event when they share the same happening time, so that two events can share one meeting room.
 */
public class Solution {
    
    private static final int START = 1;

    private static final int END = 0;
    
    private class Event {
        int time;
        int type; // end event is 0; start event is 1

        public Event(int time, int type) {
            this.time = time;
            this.type = type;
        }
    }
    
    public int minMeetingRooms(Interval[] intervals) {
        int rooms = 0; // occupied meeting rooms
        int res = 0;

        // initialize an event queue based on event's happening time
        Queue<Event> events = new PriorityQueue<>(new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                // for same time, let END event happens first to save rooms
                return e1.time != e2.time ? 
                       e1.time - e2.time : e1.type - e2.type;
            }
        });

        // create event and push into event queue
        for (Interval interval : intervals) {
            events.offer(new Event(interval.start, START));
            events.offer(new Event(interval.end, END));
        }
        
        // process events
        while (!events.isEmpty()) {
            Event event = events.poll();
            if (event.type == START) {
                rooms++;
                res = Math.max(res, rooms);
            } else {
                rooms--; 
            }
        }
        
        return res;
    }

}

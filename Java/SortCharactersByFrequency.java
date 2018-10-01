public String frequencySort(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        // Character[] is not enough since there may be mutiple chars have same freq
        List<Character>[] bucket = new ArrayList[s.length() + 1]; // freq possible from one to s.length
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(bucket[entry.getValue()] != null) {
                List<Character> list = bucket[entry.getValue()];  // !! should be List<> not ArrayList<>
                list.add(entry.getKey());
            }
            else {
                bucket[entry.getValue()] = new ArrayList<>(); // new ArrayList<>(entry.getKey()); is not right
                bucket[entry.getValue()].add(entry.getKey());
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = bucket.length - 1; i >= 0; i--) {
            if(bucket[i] != null) {
                List<Character> list = bucket[i];
                for(char c : list) {
                    /*int count = i;
                    while(count > 0) {
                        sb.append(c);
                        count--;
                    }*/
                    for (int j = 0; j < map.get(c); j++)
                        sb.append(c);
                }
            }
        }
        
        return sb.toString();
        
    }

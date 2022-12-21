(ns advent-of-code.day-06)

(defn find_marker [len pos input]
  (if (== len (count (set (take len input)))) 
    (+ pos len) 
    (find_marker len (inc pos) (drop 1 input))))

(defn part-1
  [input]
  (find_marker 4 0 (seq input)))

(defn part-2
  [input]
  (find_marker 14 0 (seq input)))
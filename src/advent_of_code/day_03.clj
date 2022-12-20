(ns advent-of-code.day-03
  (:require [clojure.string :as str ] 
            [clojure.set :as set])
)

(defn score [letter]
  (let [c (int letter)]
    (cond 
      (> c 90) (- c 96)
      :else (- c 38)
    )))

(defn find_common [line]
  (let [i (/ (count line) 2)
        parts (map set (split-at i line)) ] 
        (first (reduce set/intersection parts))))

(defn find_badge [group]
    (first (reduce set/intersection (map set group)))
)

(defn part-1
  "Day 03 Part 1"
  [input]
  (reduce + (map (comp score find_common) (str/split-lines input))))

(defn part-2
  "Day 03 Part 2"
  [input]
  (reduce + (map (comp score find_badge) (partition 3 (str/split-lines input)))))

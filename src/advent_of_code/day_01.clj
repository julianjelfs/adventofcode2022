(ns advent-of-code.day-01
  (:require [clojure.string :as str])
)

(defn sum-groups [group]
  (reduce + (map read-string group))
)

(defn not-empty? [s]
  (not (every? empty? s))
)

(defn calories_by_elf [input]
   (map sum-groups (filter not-empty? (partition-by str/blank? (str/split-lines input))))
)

(defn part-1
  "Day 01 Part 1"
  [input]
   (apply max (calories_by_elf input)))

(defn part-2
  "Day 01 Part 2"
  [input]
   (reduce + (take 3 (sort > (calories_by_elf input)))))

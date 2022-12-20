(ns advent-of-code.day-04
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn to-range [[from to]] (set (range from (inc to))))

(defn split-range [s]
  (map read-string (str/split s #"-")))

(defn to-ranges [line]
  (->> (str/split line #",") (map split-range) (map to-range)))

(defn overlap? [line]
  (let [[one two] (to-ranges line)]
        (or 
          (empty? (set/difference one two))
          (empty? (set/difference two one)))))

(defn intersect? [line]
  (let [[one two] (to-ranges line)]
        (not (empty? (set/intersection one two)))))

(defn solve
  [input strategy]
  (->> 
    (str/split-lines input) 
    (map strategy) 
    (filter identity) 
    count))

(defn part-1
  "Day 04 Part 1"
  [input]
  (solve input overlap?))

(defn part-2
  "Day 04 Part 2"
  [input]
  (solve input intersect?))

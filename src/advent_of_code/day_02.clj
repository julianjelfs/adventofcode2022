(ns advent-of-code.day-02
  (:require [clojure.string :as str])
)

(defn translate [them me]
  (cond 
    (and (= them "A") (= me "X")) "Z"
    (and (= them "A") (= me "Y")) "X"
    (and (= them "A") (= me "Z")) "Y"
    (and (= them "B") (= me "X")) "X"
    (and (= them "B") (= me "Y")) "Y"
    (and (= them "B") (= me "Z")) "Z"
    (and (= them "C") (= me "X")) "Y"
    (and (= them "C") (= me "Y")) "Z"
    (and (= them "C") (= me "Z")) "X"
  )
)

(defn nil-translate [them me] me)

(defn score [line trans]
  (let [move (str/split line #" ")
      them (first move)
      me (trans them (second move))]
  (cond 
    (and (= them "A") (= me "X")) (+ 3 1)
    (and (= them "A") (= me "Y")) (+ 6 2)
    (and (= them "A") (= me "Z")) (+ 0 3)
    (and (= them "B") (= me "X")) (+ 0 1)
    (and (= them "B") (= me "Y")) (+ 3 2)
    (and (= them "B") (= me "Z")) (+ 6 3)
    (and (= them "C") (= me "X")) (+ 6 1)
    (and (= them "C") (= me "Y")) (+ 0 2)
    (and (= them "C") (= me "Z")) (+ 3 3)
  :else 0)
))

(defn part-1
  "Day 02 Part 1"
  [input]
  (reduce + (map #(score % nil-translate) (str/split-lines input))))

(defn part-2
  "Day 02 Part 2"
  [input]
  (reduce + (map #(score % translate) (str/split-lines input))))
